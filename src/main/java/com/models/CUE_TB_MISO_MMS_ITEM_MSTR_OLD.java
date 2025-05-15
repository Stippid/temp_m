package com.models;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
@Entity
@Table(name = "cue_tb_miso_mms_item_mstr", uniqueConstraints = {
@UniqueConstraint(columnNames = "id"),})
public class CUE_TB_MISO_MMS_ITEM_MSTR_OLD {
	private String prf_group;
	public String getPrf_group() {
		return prf_group;
	}
	public void setPrf_group(String prf_group) {
		this.prf_group = prf_group;
	}
	public String getCos_sec_no() {
		return cos_sec_no;
	}
	public void setCos_sec_no(String cos_sec_no) {
		this.cos_sec_no = cos_sec_no;
	}
	public String getItem_code() {
		return item_code;
	}
	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}
	public String getItem_type() {
		return item_type;
	}
	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}
	public String getItem_seq_no() {
		return item_seq_no;
	}
	public void setItem_seq_no(String item_seq_no) {
		this.item_seq_no = item_seq_no;
	}
	public String getCreation_by() {
		return creation_by;
	}
	public void setCreation_by(String creation_by) {
		this.creation_by = creation_by;
	}
	public Double getWwr_normal() {
		return wwr_normal;
	}
	public void setWwr_normal(Double wwr_normal) {
		this.wwr_normal = wwr_normal;
	}
	public Integer getWwr_intensity() {
		return wwr_intensity;
	}
	public void setWwr_intensity(Integer wwr_intensity) {
		this.wwr_intensity = wwr_intensity;
	}
	public String getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(String creation_date) {
		this.creation_date = creation_date;
	}
	public String getApprove_by() {
		return approve_by;
	}
	public void setApprove_by(String approve_by) {
		this.approve_by = approve_by;
	}
	public String getApprove_date() {
		return approve_date;
	}
	public void setApprove_date(String approve_date) {
		this.approve_date = approve_date;
	}
	public String getUpdate_by() {
		return update_by;
	}
	public void setUpdate_by(String update_by) {
		this.update_by = update_by;
	}
	public String getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}
	public Integer getVersion_no() {
		return version_no;
	}
	public void setVersion_no(Integer version_no) {
		this.version_no = version_no;
	}
	public String getSoftdelete() {
		return softdelete;
	}
	public void setSoftdelete(String softdelete) {
		this.softdelete = softdelete;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getCategory_code() {
		return category_code;
	}
	public void setCategory_code(Integer category_code) {
		this.category_code = category_code;
	}
	public Integer getItem_group() {
		return item_group;
	}
	public void setItem_group(Integer item_group) {
		this.item_group = item_group;
	}
	public Integer getCritical_equipment() {
		return critical_equipment;
	}
	public void setCritical_equipment(Integer critical_equipment) {
		this.critical_equipment = critical_equipment;
	}
	public String getAcc_units() {
		return acc_units;
	}
	public void setAcc_units(String acc_units) {
		this.acc_units = acc_units;
	}
	public String getEngr_stores_origin() {
		return engr_stores_origin;
	}
	public void setEngr_stores_origin(String engr_stores_origin) {
		this.engr_stores_origin = engr_stores_origin;
	}
	public String getPrf_group_ue() {
		return prf_group_ue;
	}
	public void setPrf_group_ue(String prf_group_ue) {
		this.prf_group_ue = prf_group_ue;
	}
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
	private String cos_sec_no;
	private String item_code;
	private String item_type;
	private String item_seq_no;
	private String creation_by;
	private Double wwr_normal;
	private Integer wwr_intensity;
	private String creation_date;
	private String approve_by;
	private String approve_date;
	private String update_by;
	private String update_date;
	private Integer version_no;
	private String softdelete;
	private String remarks;
	private Integer status;
	private Integer category_code;
	private Integer item_group;
	private Integer critical_equipment;
	private String acc_units;
	private String engr_stores_origin;
	private String prf_group_ue;
	
}
