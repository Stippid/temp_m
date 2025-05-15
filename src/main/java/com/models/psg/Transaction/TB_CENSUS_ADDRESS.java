package com.models.psg.Transaction;



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

@Table(name = "tb_psg_census_address", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })

public class TB_CENSUS_ADDRESS {

	

	

	private int id;

	private int cen_id;

	private BigInteger comm_id;

	

	private int pre_tesil;

	private String authority;

	private Date date_authority;

	private int pre_state;

	private int pre_district;

	private int pre_country;
	private String pre_country_other;
	private String pre_domicile_state_other;
	private String pre_domicile_district_other;
	private String pre_domicile_tesil_other;
	private String per_home_addr_country_other;
	private String per_home_addr_state_other;
	private String per_home_addr_district_other;
	private String per_home_addr_tehsil_other;
	private String pers_addr_country_other;
	private String pers_addr_state_other;
	private String pers_addr_district_other;
	private String pers_addr_tehsil_other;
	
	   

	

	private int permanent_state;

	private int permanent_country;

	private String permanent_rural_urban_semi;

	private String permanent_border_area;

	private int permanent_district;

	private String permanent_village;

	private int permanent_pin_code;

	private String permanent_near_railway_station;

	private int permanent_tehsil;

	private int present_state;

	private int present_district;

	private int present_country;

	private String present_village;

	private int present_pin_code;

	private String present_near_railway_station;

	private String present_rural_urban_semi;

	private int present_tehsil;

	

	

	private int spouse_state;

	private int spouse_district;

	private int spouse_country;

	private String spouse_village;

	private int spouse_pin_code;

	private String spouse_near_railway_station;

	private int spouse_tehsil;

	private String spouse_addr_tehsil_other;
	private String spouse_addr_country_other;
	private String spouse_addr_state_other;
	private String spouse_addr_district_other;
	private String spouse_rural_urban_semi;

	

	private int status;

	private Date created_date;

	private String created_by;

	private Date modified_date;

	private String modified_by;

	private int cancel_status=-1;
	private String cancel_by;
	private Date cancel_date;
	private String stn_hq_sus_no;
	private String reject_remarks;
	
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

	@Id

	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)

	public int getId() {

		return id;

	}

	public void setId(int id) {

		this.id = id;

	}

	public int getCen_id() {

		return cen_id;

	}

	public void setCen_id(int cen_id) {

		this.cen_id = cen_id;

	}

	public int getPermanent_state() {

		return permanent_state;

	}

	public void setPermanent_state(int permanent_state) {

		this.permanent_state = permanent_state;

	}

	public int getPermanent_district() {

		return permanent_district;

	}

	public void setPermanent_district(int permanent_district) {

		this.permanent_district = permanent_district;

	}

	

	public int getPermanent_pin_code() {

		return permanent_pin_code;

	}

	public void setPermanent_pin_code(int permanent_pin_code) {

		this.permanent_pin_code = permanent_pin_code;

	}

	public String getPermanent_near_railway_station() {

		return permanent_near_railway_station;

	}

	public void setPermanent_near_railway_station(String permanent_near_railway_station) {

		this.permanent_near_railway_station = permanent_near_railway_station;

	}

	

	

	public int getPresent_state() {

		return present_state;

	}

	public void setPresent_state(int present_state) {

		this.present_state = present_state;

	}

	public int getPresent_district() {

		return present_district;

	}

	public void setPresent_district(int present_district) {

		this.present_district = present_district;

	}

	

	public String getPermanent_village() {

		return permanent_village;

	}

	public void setPermanent_village(String permanent_village) {

		this.permanent_village = permanent_village;

	}

	public String getPresent_village() {

		return present_village;

	}

	public void setPresent_village(String present_village) {

		this.present_village = present_village;

	}

	public int getPresent_pin_code() {

		return present_pin_code;

	}

	public void setPresent_pin_code(int present_pin_code) {

		this.present_pin_code = present_pin_code;

	}

	public String getPresent_near_railway_station() {

		return present_near_railway_station;

	}

	public void setPresent_near_railway_station(String present_near_railway_station) {

		this.present_near_railway_station = present_near_railway_station;

	}

	

	public int getPresent_tehsil() {

		return present_tehsil;

	}

	public void setPresent_tehsil(int present_tehsil) {

		this.present_tehsil = present_tehsil;

	}



	public int getPre_state() {

		return pre_state;

	}

	public void setPre_state(int pre_state) {

		this.pre_state = pre_state;

	}

	public int getPre_district() {

		return pre_district;

	}

	public void setPre_district(int pre_district) {

		this.pre_district = pre_district;

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

	public Date getModified_date() {

		return modified_date;

	}

	public void setModified_date(Date modified_date) {

		this.modified_date = modified_date;

	}

	public String getModified_by() {

		return modified_by;

	}

	public void setModified_by(String modified_by) {

		this.modified_by = modified_by;

	}

	

	public int getPre_tesil() {

		return pre_tesil;

	}

	public void setPre_tesil(int pre_tesil) {

		this.pre_tesil = pre_tesil;

	}

	public String getAuthority() {

		return authority;

	}

	public void setAuthority(String authority) {

		this.authority = authority;

	}

	public Date getDate_authority() {

		return date_authority;

	}

	public void setDate_authority(Date date_authority) {

		this.date_authority = date_authority;

	}

	public int getPre_country() {

		return pre_country;

	}

	public void setPre_country(int pre_country) {

		this.pre_country = pre_country;

	}

	public int getPermanent_country() {

		return permanent_country;

	}

	public void setPermanent_country(int permanent_country) {

		this.permanent_country = permanent_country;

	}

	public int getPresent_country() {

		return present_country;

	}

	public void setPresent_country(int present_country) {

		this.present_country = present_country;

	}

	

	public String getPermanent_rural_urban_semi() {

		return permanent_rural_urban_semi;

	}

	public void setPermanent_rural_urban_semi(String permanent_rural_urban_semi) {

		this.permanent_rural_urban_semi = permanent_rural_urban_semi;

	}

	public String getPermanent_border_area() {

		return permanent_border_area;

	}

	public void setPermanent_border_area(String permanent_border_area) {

		this.permanent_border_area = permanent_border_area;

	}

	public String getPresent_rural_urban_semi() {

		return present_rural_urban_semi;

	}

	public void setPresent_rural_urban_semi(String present_rural_urban_semi) {

		this.present_rural_urban_semi = present_rural_urban_semi;

	}

	

	



	

	public int getStatus() {

		return status;

	}

	public void setStatus(int status) {

		this.status = status;

	}

	

	public BigInteger getComm_id() {
		return comm_id;
	}
	
	public void setComm_id(BigInteger comm_id) {
		this.comm_id = comm_id;
	}
	
	public int getPermanent_tehsil() {

		return permanent_tehsil;

	}

	public void setPermanent_tehsil(int permanent_tehsil) {

		this.permanent_tehsil = permanent_tehsil;

	}

	public int getSpouse_state() {

		return spouse_state;

	}

	public void setSpouse_state(int spouse_state) {

		this.spouse_state = spouse_state;

	}

	public int getSpouse_district() {

		return spouse_district;

	}

	public void setSpouse_district(int spouse_district) {

		this.spouse_district = spouse_district;

	}

	public int getSpouse_country() {

		return spouse_country;

	}

	public void setSpouse_country(int spouse_country) {

		this.spouse_country = spouse_country;

	}

	public String getSpouse_village() {

		return spouse_village;

	}

	public void setSpouse_village(String spouse_village) {

		this.spouse_village = spouse_village;

	}

	public int getSpouse_pin_code() {

		return spouse_pin_code;

	}

	public void setSpouse_pin_code(int spouse_pin_code) {

		this.spouse_pin_code = spouse_pin_code;

	}

	public String getSpouse_near_railway_station() {

		return spouse_near_railway_station;

	}

	public void setSpouse_near_railway_station(String spouse_near_railway_station) {

		this.spouse_near_railway_station = spouse_near_railway_station;

	}

	public int getSpouse_tehsil() {

		return spouse_tehsil;

	}

	public void setSpouse_tehsil(int spouse_tehsil) {

		this.spouse_tehsil = spouse_tehsil;

	}

	public String getPre_country_other() {
		return pre_country_other;
	}

	public void setPre_country_other(String pre_country_other) {
		this.pre_country_other = pre_country_other;
	}

	public String getPre_domicile_state_other() {
		return pre_domicile_state_other;
	}

	public void setPre_domicile_state_other(String pre_domicile_state_other) {
		this.pre_domicile_state_other = pre_domicile_state_other;
	}

	public String getPre_domicile_district_other() {
		return pre_domicile_district_other;
	}

	public void setPre_domicile_district_other(String pre_domicile_district_other) {
		this.pre_domicile_district_other = pre_domicile_district_other;
	}

	public String getPre_domicile_tesil_other() {
		return pre_domicile_tesil_other;
	}

	public void setPre_domicile_tesil_other(String pre_domicile_tesil_other) {
		this.pre_domicile_tesil_other = pre_domicile_tesil_other;
	}

	

	public String getPer_home_addr_country_other() {
		return per_home_addr_country_other;
	}

	public void setPer_home_addr_country_other(String per_home_addr_country_other) {
		this.per_home_addr_country_other = per_home_addr_country_other;
	}

	public String getPer_home_addr_state_other() {
		return per_home_addr_state_other;
	}

	public void setPer_home_addr_state_other(String per_home_addr_state_other) {
		this.per_home_addr_state_other = per_home_addr_state_other;
	}

	public String getPer_home_addr_district_other() {
		return per_home_addr_district_other;
	}

	public void setPer_home_addr_district_other(String per_home_addr_district_other) {
		this.per_home_addr_district_other = per_home_addr_district_other;
	}

	public String getPer_home_addr_tehsil_other() {
		return per_home_addr_tehsil_other;
	}

	public void setPer_home_addr_tehsil_other(String per_home_addr_tehsil_other) {
		this.per_home_addr_tehsil_other = per_home_addr_tehsil_other;
	}

	public String getPers_addr_country_other() {
		return pers_addr_country_other;
	}

	public void setPers_addr_country_other(String pers_addr_country_other) {
		this.pers_addr_country_other = pers_addr_country_other;
	}

	public String getPers_addr_state_other() {
		return pers_addr_state_other;
	}

	public void setPers_addr_state_other(String pers_addr_state_other) {
		this.pers_addr_state_other = pers_addr_state_other;
	}

	public String getPers_addr_district_other() {
		return pers_addr_district_other;
	}

	public void setPers_addr_district_other(String pers_addr_district_other) {
		this.pers_addr_district_other = pers_addr_district_other;
	}

	public String getPers_addr_tehsil_other() {
		return pers_addr_tehsil_other;
	}

	public void setPers_addr_tehsil_other(String pers_addr_tehsil_other) {
		this.pers_addr_tehsil_other = pers_addr_tehsil_other;
	}

	public String getSpouse_addr_tehsil_other() {
		return spouse_addr_tehsil_other;
	}

	public void setSpouse_addr_tehsil_other(String spouse_addr_tehsil_other) {
		this.spouse_addr_tehsil_other = spouse_addr_tehsil_other;
	}

	public String getSpouse_addr_country_other() {
		return spouse_addr_country_other;
	}

	public void setSpouse_addr_country_other(String spouse_addr_country_other) {
		this.spouse_addr_country_other = spouse_addr_country_other;
	}

	public String getSpouse_addr_state_other() {
		return spouse_addr_state_other;
	}

	public void setSpouse_addr_state_other(String spouse_addr_state_other) {
		this.spouse_addr_state_other = spouse_addr_state_other;
	}

	public String getSpouse_addr_district_other() {
		return spouse_addr_district_other;
	}

	public void setSpouse_addr_district_other(String spouse_addr_district_other) {
		this.spouse_addr_district_other = spouse_addr_district_other;
	}
	public String getSpouse_rural_urban_semi() {
		return spouse_rural_urban_semi;
	}
	public void setSpouse_rural_urban_semi(String spouse_rural_urban_semi) {
		this.spouse_rural_urban_semi = spouse_rural_urban_semi;
	}
	public String getStn_hq_sus_no() {
		return stn_hq_sus_no;
	}
	public void setStn_hq_sus_no(String stn_hq_sus_no) {
		this.stn_hq_sus_no = stn_hq_sus_no;
	}
	public String getReject_remarks() {
		return reject_remarks;
	}
	public void setReject_remarks(String reject_remarks) {
		this.reject_remarks = reject_remarks;
	}



}

