package com.models.psg.update_census_data;


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
@Table(name = "tb_psg_census_awardsnmedal", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
public class TB_CENSUS_AWARDSNMEDAL {

	private int id;
	private int census_id;
	private String category_8;
	private String select_desc;
	private Date date_of_award;
	private String unit;
	private String bde;
	private String div_subarea;
	private String corps_area;
	private String command;
	private String created_by;
	private Date created_on;
	private String modify_by;
	private Date modify_on;
	private int status;
	private BigInteger comm_id;
	private String authority;
	private Date date_of_authority;
	private int cancel_status=-1;
	private String cancel_by;
	private Date cancel_date;
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
	public int getCensus_id() {
		return census_id;
	}
	public void setCensus_id(int census_id) {
		this.census_id = census_id;
	}
	public String getCategory_8() {
		return category_8;
	}
	public void setCategory_8(String category_8) {
		this.category_8 = category_8;
	}
	public Date getDate_of_award() {
		return date_of_award;
	}
	public void setDate_of_award(Date date_of_award) {
		this.date_of_award = date_of_award;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getBde() {
		return bde;
	}
	public void setBde(String bde) {
		this.bde = bde;
	}
	public String getDiv_subarea() {
		return div_subarea;
	}
	public void setDiv_subarea(String div_subarea) {
		this.div_subarea = div_subarea;
	}
	public String getCorps_area() {
		return corps_area;
	}
	public void setCorps_area(String corps_area) {
		this.corps_area = corps_area;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
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
	public String getModify_by() {
		return modify_by;
	}
	public void setModify_by(String modify_by) {
		this.modify_by = modify_by;
	}
	public Date getModify_on() {
		return modify_on;
	}
	public void setModify_on(Date modify_on) {
		this.modify_on = modify_on;
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
	public String getSelect_desc() {
		return select_desc;
	}
	public void setSelect_desc(String select_desc) {
		this.select_desc = select_desc;
	}
	public String getReject_remarks() {
		return reject_remarks;
	}
	public void setReject_remarks(String reject_remarks) {
		this.reject_remarks = reject_remarks;
	}
	
	
	
	
}
