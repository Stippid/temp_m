package com.models.mnh;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tb_med_d_disease_cause", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class Tb_Med_Disease_Cause {
	private int id;
	
	private String icd_code;
	private String icd_description;
	private String disease_mmr;
	private String disease_aso;
	private String disease_principal;
	private String disease_type;
	private String block_description;
	private String disease_subtype;
	private String disease_family;
	private String disease_children;
	private String disease_cr_type;
	private String disease_cr_block_description;
	private String principle_dc_flag;
	private String notifiable_dc_flag;
	private String current_record_flag;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date record_valid_to;
	private String monthly_flag;
	private String short_form;
	private String short_desc;
	private String type;
	private String remarks;
	private Date created_on;
	private Date modified_on;
	private Date record_valid_from;
	private String created_by;
	private String modified_by;

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
	public String getIcd_code() {
		return icd_code;
	}
	public void setIcd_code(String icd_code) {
		this.icd_code = icd_code;
	}
	@Column(length = 255)
	public String getIcd_description() {
		return icd_description;
	}
	public void setIcd_description(String icd_description) {
		this.icd_description = icd_description;
	}
	@Column(length = 100)
	public String getDisease_mmr() {
		return disease_mmr;
	}
	public void setDisease_mmr(String disease_mmr) {
		this.disease_mmr = disease_mmr;
	}
	@Column(length = 100)
	public String getDisease_aso() {
		return disease_aso;
	}
	public void setDisease_aso(String disease_aso) {
		this.disease_aso = disease_aso;
	}
	@Column(length = 255)
	public String getDisease_principal() {
		return disease_principal;
	}
	public void setDisease_principal(String disease_principal) {
		this.disease_principal = disease_principal;
	}
	@Column(length = 255)
	public String getDisease_type() {
		return disease_type;
	}
	public void setDisease_type(String disease_type) {
		this.disease_type = disease_type;
	}
	@Column(length = 255)
	public String getBlock_description() {
		return block_description;
	}
	public void setBlock_description(String block_description) {
		this.block_description = block_description;
	}
	@Column(length = 255)
	public String getDisease_subtype() {
		return disease_subtype;
	}
	public void setDisease_subtype(String disease_subtype) {
		this.disease_subtype = disease_subtype;
	}
	@Column(length = 50)
	public String getDisease_family() {
		return disease_family;
	}
	public void setDisease_family(String disease_family) {
		this.disease_family = disease_family;
	}
	@Column(length = 50)
	public String getDisease_children() {
		return disease_children;
	}
	public void setDisease_children(String disease_children) {
		this.disease_children = disease_children;
	}
	@Column(length = 255)
	public String getDisease_cr_type() {
		return disease_cr_type;
	}
	public void setDisease_cr_type(String disease_cr_type) {
		this.disease_cr_type = disease_cr_type;
	}
	@Column(length = 255)
	public String getDisease_cr_block_description() {
		return disease_cr_block_description;
	}
	public void setDisease_cr_block_description(String disease_cr_block_description) {
		this.disease_cr_block_description = disease_cr_block_description;
	}
	@Column(length = 1)
	public String getPrinciple_dc_flag() {
		return principle_dc_flag;
	}
	public void setPrinciple_dc_flag(String principle_dc_flag) {
		this.principle_dc_flag = principle_dc_flag;
	}
	@Column(length = 1)
	public String getNotifiable_dc_flag() {
		return notifiable_dc_flag;
	}
	public void setNotifiable_dc_flag(String notifiable_dc_flag) {
		this.notifiable_dc_flag = notifiable_dc_flag;
	}
	@Column(length = 1)
	public String getCurrent_record_flag() {
		return current_record_flag;
	}
	public void setCurrent_record_flag(String current_record_flag) {
		this.current_record_flag = current_record_flag;
	}
	
	public Date getRecord_valid_to() {
		return record_valid_to;
	}
	public void setRecord_valid_to(Date record_valid_to) {
		this.record_valid_to = record_valid_to;
	}
	@Column(length = 255)
	public String getMonthly_flag() {
		return monthly_flag;
	}
	public void setMonthly_flag(String monthly_flag) {
		this.monthly_flag = monthly_flag;
	}
	@Column(length = 100)
	public String getShort_form() {
		return short_form;
	}
	public void setShort_form(String short_form) {
		this.short_form = short_form;
	}
	@Column(length = 250)
	public String getShort_desc() {
		return short_desc;
	}
	public void setShort_desc(String short_desc) {
		this.short_desc = short_desc;
	}
	@Column(length = 50)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(length = 255)
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Date getCreated_on() {
		return created_on;
	}
	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}
	public Date getModified_on() {
		return modified_on;
	}
	public void setModified_on(Date modified_on) {
		this.modified_on = modified_on;
	}
	public Date getRecord_valid_from() {
		return record_valid_from;
	}
	public void setRecord_valid_from(Date record_valid_from) {
		this.record_valid_from = record_valid_from;
	}
	@Column(length = 35)
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	@Column(length = 35)
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	
/*	private Set<Tb_Med_Daily_Hosp_Adm_Off_Vip> vipicd_code = new HashSet<Tb_Med_Daily_Hosp_Adm_Off_Vip>(0);
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "v_icd_code")
	public Set<Tb_Med_Daily_Hosp_Adm_Off_Vip> getVipicd_code() {
		return vipicd_code;
	}
	public void setVipicd_code(Set<Tb_Med_Daily_Hosp_Adm_Off_Vip> vipicd_code) {
		this.vipicd_code = vipicd_code;
	}*/
	
	
/*    private Set<Tb_Med_Daily_Hosp_Adm_Amc> admicd_code = new HashSet<Tb_Med_Daily_Hosp_Adm_Amc>(0);
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "adm_icd_code")
	public Set<Tb_Med_Daily_Hosp_Adm_Amc> getAdmicd_code() {
		return admicd_code;
	}
	public void setAdmicd_code(Set<Tb_Med_Daily_Hosp_Adm_Amc> admicd_code) {
		this.admicd_code = admicd_code;
	}*/
	
	
}