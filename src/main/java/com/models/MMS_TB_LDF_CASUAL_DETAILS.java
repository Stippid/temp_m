/*package com.models;

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
@Table(name = "mms_tb_ldf_casual_details", uniqueConstraints = {@UniqueConstraint(columnNames = "id"),})
public class MMS_TB_LDF_CASUAL_DETAILS {

	private int id;
	private String sus_no;
	private Date fire_date;
	private String fire_time;
	private String fire_cause;
	private String type_article_lost;
	private String item_no;
	private String item_des;
	private String qty_lost;
	private float amount_lost;
	private Date sys_date;
	private int version_no;
	private String remarks;
	private String creation_by;
	private Date creation_date;
	private String approve_by;
	private Date approve_date;
	private String update_by;
	private Date update_date;
	private String formation_sus_no;
	private String softdelete;
	private String nature_occupancy;
	private String acc_unit;
	private String nature_of_occupancy_noc;
	private String des_of_artcl;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSus_no() {
		return sus_no;
	}
	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}
	public Date getFire_date() {
		return fire_date;
	}
	public void setFire_date(Date fire_date) {
		this.fire_date = fire_date;
	}
	public String getFire_time() {
		return fire_time;
	}
	public void setFire_time(String fire_time) {
		this.fire_time = fire_time;
	}
	public String getFire_cause() {
		return fire_cause;
	}
	public void setFire_cause(String fire_cause) {
		this.fire_cause = fire_cause;
	}
	public String getType_article_lost() {
		return type_article_lost;
	}
	public void setType_article_lost(String type_article_lost) {
		this.type_article_lost = type_article_lost;
	}
	public String getItem_no() {
		return item_no;
	}
	public void setItem_no(String item_no) {
		this.item_no = item_no;
	}
	public String getItem_des() {
		return item_des;
	}
	public void setItem_des(String item_des) {
		this.item_des = item_des;
	}
	public String getQty_lost() {
		return qty_lost;
	}
	public void setQty_lost(String qty_lost) {
		this.qty_lost = qty_lost;
	}
	public float getAmount_lost() {
		return amount_lost;
	}
	public void setAmount_lost(float amount_lost) {
		this.amount_lost = amount_lost;
	}
	public Date getSys_date() {
		return sys_date;
	}
	public void setSys_date(Date sys_date) {
		this.sys_date = sys_date;
	}
	public int getVersion_no() {
		return version_no;
	}
	public void setVersion_no(int version_no) {
		this.version_no = version_no;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	public String getApprove_by() {
		return approve_by;
	}
	public void setApprove_by(String approve_by) {
		this.approve_by = approve_by;
	}
	public Date getApprove_date() {
		return approve_date;
	}
	public void setApprove_date(Date approve_date) {
		this.approve_date = approve_date;
	}
	public String getUpdate_by() {
		return update_by;
	}
	public void setUpdate_by(String update_by) {
		this.update_by = update_by;
	}
	public Date getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}
	public String getFormation_sus_no() {
		return formation_sus_no;
	}
	public void setFormation_sus_no(String formation_sus_no) {
		this.formation_sus_no = formation_sus_no;
	}
	public String getSoftdelete() {
		return softdelete;
	}
	public void setSoftdelete(String softdelete) {
		this.softdelete = softdelete;
	}
	public String getNature_occupancy() {
		return nature_occupancy;
	}
	public void setNature_occupancy(String nature_occupancy) {
		this.nature_occupancy = nature_occupancy;
	}
	public String getAcc_unit() {
		return acc_unit;
	}
	public void setAcc_unit(String acc_unit) {
		this.acc_unit = acc_unit;
	}
	public String getNature_of_occupancy_noc() {
		return nature_of_occupancy_noc;
	}
	public void setNature_of_occupancy_noc(String nature_of_occupancy_noc) {
		this.nature_of_occupancy_noc = nature_of_occupancy_noc;
	}
	public String getDes_of_artcl() {
		return des_of_artcl;
	}
	public void setDes_of_artcl(String des_of_artcl) {
		this.des_of_artcl = des_of_artcl;
	}
	
	

}
*/