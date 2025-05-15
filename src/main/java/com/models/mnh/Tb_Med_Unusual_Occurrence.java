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
@Table(name = "tb_med_unusual_occurrence", uniqueConstraints = {
@UniqueConstraint(columnNames = "id")})
public class Tb_Med_Unusual_Occurrence {
	private int id;
	private String persnl_no;
	//private int rank;
	private String category;
	private String appointment;
	private String persnl_name;
	private int age_year;
	private BigInteger adhar_card_no;
	private String persnl_unit;
	private String diagnosis;
	private String remarks;
	private String sus_no;
	private Date date_time_incident;
	private Date date_time_admission;
	private Date date_of_birth;
	private String service;
	private String type;
	private String sex;
	private String relation;
	private Date dt_report;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
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
	@Column(length = 20)
	public String getPersnl_no() {
		return persnl_no;
	}
	public void setPersnl_no(String persnl_no) {
		this.persnl_no = persnl_no;
	}
	@Column(length = 10)
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Column(length = 20)
	public String getAppointment() {
		return appointment;
	}
	public void setAppointment(String appointment) {
		this.appointment = appointment;
	}
	@Column(length = 100)
	public String getPersnl_name() {
		return persnl_name;
	}
	public void setPersnl_name(String persnl_name) {
		this.persnl_name = persnl_name;
	}
	@Column(length = 100)
	public String getPersnl_unit() {
		return persnl_unit;
	}
	public void setPersnl_unit(String persnl_unit) {
		this.persnl_unit = persnl_unit;
	}
	@Column(length = 255)
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	@Column(length = 250)
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Column(length = 8)
	public String getSus_no() {
		return sus_no;
	}
	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}
	@Column(length = 10)
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
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
	public int getAge_year() {
		return age_year;
	}
	public void setAge_year(int age_year) {
		this.age_year = age_year;
	}
	
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public Date getDate_time_incident() {
		return date_time_incident;
	}
	public void setDate_time_incident(Date date_time_incident) {
		this.date_time_incident = date_time_incident;
	}
	public Date getDate_time_admission() {
		return date_time_admission;
	}
	public void setDate_time_admission(Date date_time_admission) {
		this.date_time_admission = date_time_admission;
	}
	@Column(length = 255)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getDt_report() {
		return dt_report;
	}
	public void setDt_report(Date dt_report) {
		this.dt_report = dt_report;
	}
	public BigInteger getAdhar_card_no() {
		return adhar_card_no;
	}
	public void setAdhar_card_no(BigInteger adhar_card_no) {
		this.adhar_card_no = adhar_card_no;
	}
	public Date getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	public BigInteger getContact_no() {
		return contact_no;
	}
	public void setContact_no(BigInteger contact_no) {
		this.contact_no = contact_no;
	}
	
	
	    private Tb_Med_RankCode uo_rank;
       @ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "rank", nullable = false)
		public Tb_Med_RankCode getUo_rank() {
			return uo_rank;
		}
		public void setUo_rank(Tb_Med_RankCode uo_rank) {
			this.uo_rank = uo_rank;
		}
}
