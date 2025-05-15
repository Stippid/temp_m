package com.models.mnh;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tb_med_death", uniqueConstraints = {
@UniqueConstraint(columnNames = "id")})
public class Tb_Med_Death {
	
	private int	id;
	private String service;
	private String persnl_no;
	private String persnl_name;
	private int age_year;
	private int age_month; 
	private int total_service_year; 
	private int total_service_month; 
	
	private String category; 
	private String persnl_unit;  
	private String gender;
	private String relation; 
	private String place_of_death;  
	private String nature_of_death;
	
	private Date date_of_death;
	private Date date_of_birth;
	
	private String operation;
	private String sector;
	private String location;
	private String bde_div_corps_comd;
	private String on_ib_ic;
	private String sus_no; 
	private String and_no;
	private String death_summary; 
	private String name_of_nok;
	private String nok_informed;
	private String address_of_nok; 
	private String remarks;
	private String cause_of_death; 
	private String icd_code;
	private String icd_desc;
	private String other_details; 
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private BigInteger adhar_card_no;
	private BigInteger contact_no;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(length = 10)
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	@Column(length = 20)
	public String getPersnl_no() {
		return persnl_no;
	}
	public void setPersnl_no(String persnl_no) {
		this.persnl_no = persnl_no;
	}
	@Column(length = 100)
	public String getPersnl_name() {
		return persnl_name;
	}
	public void setPersnl_name(String persnl_name) {
		this.persnl_name = persnl_name;
	}
	public int getAge_year() {
		return age_year;
	}
	public void setAge_year(int age_year) {
		this.age_year = age_year;
	}
	public int getAge_month() {
		return age_month;
	}
	public void setAge_month(int age_month) {
		this.age_month = age_month;
	}
	public int getTotal_service_year() {
		return total_service_year;
	}
	public void setTotal_service_year(int total_service_year) {
		this.total_service_year = total_service_year;
	}
	public int getTotal_service_month() {
		return total_service_month;
	}
	public void setTotal_service_month(int total_service_month) {
		this.total_service_month = total_service_month;
	}
	
	@Column(length = 10)
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Date getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	@Column(length = 100)
	public String getPersnl_unit() {
		return persnl_unit;
	}
	public void setPersnl_unit(String persnl_unit) {
		this.persnl_unit = persnl_unit;
	}
	@Column(length = 10)
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Column(length = 10)
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	@Column(length = 200)
	public String getPlace_of_death() {
		return place_of_death;
	}
	public void setPlace_of_death(String place_of_death) {
		this.place_of_death = place_of_death;
	}
	@Column(length = 50)
	public String getNature_of_death() {
		return nature_of_death;
	}
	public void setNature_of_death(String nature_of_death) {
		this.nature_of_death = nature_of_death;
	}
	
	public Date getDate_of_death() {
		return date_of_death;
	}
	public void setDate_of_death(Date date_of_death) {
		this.date_of_death = date_of_death;
	}
	@Column(length = 100)
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	@Column(length = 50)
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	@Column(length = 100)
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Column(length = 100)
	public String getBde_div_corps_comd() {
		return bde_div_corps_comd;
	}
	public void setBde_div_corps_comd(String bde_div_corps_comd) {
		this.bde_div_corps_comd = bde_div_corps_comd;
	}
	@Column(length = 10)
	public String getOn_ib_ic() {
		return on_ib_ic;
	}
	public void setOn_ib_ic(String on_ib_ic) {
		this.on_ib_ic = on_ib_ic;
	}
	@Column(length = 8)
	public String getSus_no() {
		return sus_no;
	}
	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}
	@Column(length = 15)
	public String getAnd_no() {
		return and_no;
	}
	public void setAnd_no(String and_no) {
		this.and_no = and_no;
	}
	@Column(length = 255)
	public String getDeath_summary() {
		return death_summary;
	}
	public void setDeath_summary(String death_summary) {
		this.death_summary = death_summary;
	}
	@Column(length = 100)
	public String getName_of_nok() {
		return name_of_nok;
	}
	public void setName_of_nok(String name_of_nok) {
		this.name_of_nok = name_of_nok;
	}
	@Column(length = 3)
	public String getNok_informed() {
		return nok_informed;
	}
	public void setNok_informed(String nok_informed) {
		this.nok_informed = nok_informed;
	}
	@Column(length = 255)
	public String getAddress_of_nok() {
		return address_of_nok;
	}
	public void setAddress_of_nok(String address_of_nok) {
		this.address_of_nok = address_of_nok;
	}
	@Column(length = 255)
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Column(length = 255)
	public String getCause_of_death() {
		return cause_of_death;
	}
	public void setCause_of_death(String cause_of_death) {
		this.cause_of_death = cause_of_death;
	}
	@Column(length = 10)
	public String getIcd_code() {
		return icd_code;
	}
	public void setIcd_code(String icd_code) {
		this.icd_code = icd_code;
	}
	@Column(length = 255)
	public String getIcd_desc() {
		return icd_desc;
	}
	public void setIcd_desc(String icd_desc) {
		this.icd_desc = icd_desc;
	}
	@Column(length = 255)
	public String getOther_details() {
		return other_details;
	}
	public void setOther_details(String other_details) {
		this.other_details = other_details;
	}
	@Column(length = 35)
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
	@Column(length = 35)
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	public Date getModified_on() {
		return modified_on;
	}
	public void setModified_on(Date modified_on) {
		this.modified_on = modified_on;
	}
	
	public BigInteger getAdhar_card_no() {
		return adhar_card_no;
	}
	public void setAdhar_card_no(BigInteger adhar_card_no) {
		this.adhar_card_no = adhar_card_no;
	}
	public BigInteger getContact_no() {
		return contact_no;
	}
	public void setContact_no(BigInteger contact_no) {
		this.contact_no = contact_no;
	}
	
	
	 private Tb_Med_RankCode death_rank;
	   @ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "rank", nullable = false)
		public Tb_Med_RankCode getDeath_rank() {
			return death_rank;
		}
		public void setDeath_rank(Tb_Med_RankCode death_rank) {
			this.death_rank = death_rank;
		}
	
	
	
}