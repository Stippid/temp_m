package com.models;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.parsers.DocumentBuilder;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "mms_tb_imp_drr_newformate", uniqueConstraints = {
@UniqueConstraint(columnNames = "id"),})

public class MMS_TB_IMP_DRR_NEWFORMATE {
	
	
	
	private String issue_sus_no;
	private String recv_sus_no;
	private String material_no;	
	private String census_no;
	private String Nomen;
	private String Brief_Desc;
	private String type_of_hldg;
	private String type_of_eqpt;
	private String cat_part_no;
	private String eqpt_batch_no;
	private String eqpt_part_no ;
	private double depot_stock;
	private String unit_price;
	private String condition;
	private String eqpt_regn_no;
	private String au;
	private String eqpt_make;
	private String eqpt_model;
	private String upload_file_name;
	private String cqa_note_date;
	private String origin_country;
	private String def_cat_no_dcan;
	private String created_by;
	private String modified_by;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date uploaded_dt = new Date();
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date created_dt;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date modified_date;

	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getMaterial_no() {
		return material_no;
	}

	public void setMaterial_no(String material_no) {
		this.material_no = material_no;
	}

	public String getCensus_no() {
		return census_no;
	}

	public void setCensus_no(String census_no) {
		this.census_no = census_no;
	}

	public String getNomen() {
		return Nomen;
	}

	public void setNomen(String nomen) {
		Nomen = nomen;
	}

	public String getBrief_Desc() {
		return Brief_Desc;
	}

	public void setBrief_Desc(String brief_Desc) {
		Brief_Desc = brief_Desc;
	}

	public String getType_of_hldg() {
		return type_of_hldg;
	}

	public void setType_of_hldg(String type_of_hldg) {
		this.type_of_hldg = type_of_hldg;
	}

	public String getType_of_eqpt() {
		return type_of_eqpt;
	}

	public void setType_of_eqpt(String type_of_eqpt) {
		this.type_of_eqpt = type_of_eqpt;
	}

	public String getCat_part_no() {
		return cat_part_no;
	}

	public void setCat_part_no(String cat_part_no) {
		this.cat_part_no = cat_part_no;
	}

	public String getEqpt_batch_no() {
		return eqpt_batch_no;
	}

	public void setEqpt_batch_no(String eqpt_batch_no) {
		this.eqpt_batch_no = eqpt_batch_no;
	}

	public String getEqpt_part_no() {
		return eqpt_part_no;
	}

	public void setEqpt_part_no(String eqpt_part_no) {
		this.eqpt_part_no = eqpt_part_no;
	}

	public double getDepot_stock() {
		return depot_stock;
	}

	public void setDepot_stock(Double depot_stock) {
		this.depot_stock = depot_stock;
	}

	public String getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(String unit_price) {
		this.unit_price = unit_price;
	}

	public String getEqpt_make() {
		return eqpt_make;
	}

	public void setEqpt_make(String eqpt_make) {
		this.eqpt_make = eqpt_make;
	}

	public String getEqpt_model() {
		return eqpt_model;
	}

	public void setEqpt_model(String eqpt_model) {
		this.eqpt_model = eqpt_model;
	}

	public String getUpload_file_name() {
		return upload_file_name;
	}

	public void setUpload_file_name(String upload_file_name) {
		this.upload_file_name = upload_file_name;
	}

	public String getCqa_note_date() {
		return cqa_note_date;
	}

	public void setCqa_note_date(String cqa_note_date) {
		this.cqa_note_date = cqa_note_date;
	}

	public String getOrigin_country() {
		return origin_country;
	}

	public void setOrigin_country(String origin_country) {
		this.origin_country = origin_country;
	}

	public String getDef_cat_no_dcan() {
		return def_cat_no_dcan;
	}

	public void setDef_cat_no_dcan(String def_cat_no_dcan) {
		this.def_cat_no_dcan = def_cat_no_dcan;
	}


	public Date getCreated_dt() {
		return created_dt;
	}

	public void setCreated_dt(Date created_dt) {
		this.created_dt = created_dt;
	}

	public Date getUploaded_dt() {
		return uploaded_dt;
	}

	public void setUploaded_dt(Date uploaded_dt) {
		this.uploaded_dt = uploaded_dt;
	}


	public String getIssue_sus_no() {
		return issue_sus_no;
	}

	public void setIssue_sus_no(String issue_sus_no) {
		this.issue_sus_no = issue_sus_no;
	}

	public String getRecv_sus_no() {
		return recv_sus_no;
	}

	public void setRecv_sus_no(String recv_sus_no) {
		this.recv_sus_no = recv_sus_no;
	}


	public String getEqpt_regn_no() {
		return eqpt_regn_no;
	}

	public void setEqpt_regn_no(String eqpt_regn_no) {
		this.eqpt_regn_no = eqpt_regn_no;
	}

	public String getAu() {
		return au;
	}

	public void setAu(String au) {
		this.au = au;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
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

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	

	
}
