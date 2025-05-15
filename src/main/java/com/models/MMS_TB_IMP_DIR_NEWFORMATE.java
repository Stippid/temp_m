package com.models;

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
@Table(name = "mms_tb_imp_dir_newformate", uniqueConstraints = {
@UniqueConstraint(columnNames = "id"),})

public class MMS_TB_IMP_DIR_NEWFORMATE {
	
	
	
	private String issuing_sus_no;
	private String receiving_sus_no;
	private String au;
	private String regn_no;
	private String ro_no;
	private String material_no;	
	private String census_no;
	private String Nomen;
	private String cat_par_no;
	private String eqpt_part_no;
	private String eqpt_serial_no;	
	private String eqpt_batch_no;
	private String unit_name;
	private String status;
	private String condition;
	private double issued_qty;
	private String unit_price;
	private String eqpt_make;
	private String eqpt_model;
	private String iv_no;
	private String iv_date;
	private String data_upd_by;
	private String data_upd_date;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date uploaded_dt = new Date();
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date created_dt;

	
	
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

	public String getCat_par_no() {
		return cat_par_no;
	}

	public void setCat_par_no(String cat_par_no) {
		this.cat_par_no = cat_par_no;
	}

	public String getEqpt_part_no() {
		return eqpt_part_no;
	}

	public void setEqpt_part_no(String eqpt_part_no) {
		this.eqpt_part_no = eqpt_part_no;
	}

	public String getEqpt_serial_no() {
		return eqpt_serial_no;
	}

	public void setEqpt_serial_no(String eqpt_serial_no) {
		this.eqpt_serial_no = eqpt_serial_no;
	}

	public String getEqpt_batch_no() {
		return eqpt_batch_no;
	}

	public void setEqpt_batch_no(String eqpt_batch_no) {
		this.eqpt_batch_no = eqpt_batch_no;
	}

	public String getUnit_name() {
		return unit_name;
	}

	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getIssued_qty() {
		return issued_qty;
	}

	public void setIssued_qty(Double issued_qty) {
		this.issued_qty = issued_qty;
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

	public String getIv_no() {
		return iv_no;
	}

	public void setIv_no(String iv_no) {
		this.iv_no = iv_no;
	}

	public String getIv_date() {
		return iv_date;
	}

	public void setIv_date(String iv_date) {
		this.iv_date = iv_date;
	}

	public String getData_upd_by() {
		return data_upd_by;
	}

	public void setData_upd_by(String data_upd_by) {
		this.data_upd_by = data_upd_by;
	}

	public String getData_upd_date() {
		return data_upd_date;
	}

	public void setData_upd_date(String data_upd_date) {
		this.data_upd_date = data_upd_date;
	}

	public Date getUploaded_dt() {
		return uploaded_dt;
	}

	public void setUploaded_dt(Date uploaded_dt) {
		this.uploaded_dt = uploaded_dt;
	}

	public Date getCreated_dt() {
		return created_dt;
	}

	public void setCreated_dt(Date created_dt) {
		this.created_dt = created_dt;
	}

	public String getIssuing_sus_no() {
		return issuing_sus_no;
	}

	public void setIssuing_sus_no(String issuing_sus_no) {
		this.issuing_sus_no = issuing_sus_no;
	}

	public String getReceiving_sus_no() {
		return receiving_sus_no;
	}

	public void setReceiving_sus_no(String receiving_sus_no) {
		this.receiving_sus_no = receiving_sus_no;
	}

	public String getRegn_no() {
		return regn_no;
	}

	public void setRegn_no(String regn_no) {
		this.regn_no = regn_no;
	}

	public String getRo_no() {
		return ro_no;
	}

	public void setRo_no(String ro_no) {
		this.ro_no = ro_no;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getAu() {
		return au;
	}

	public void setAu(String au) {
		this.au = au;
	}


	

	
}
