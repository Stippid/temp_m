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
@Table(name = "tb_tms_banum_dirctry", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class TB_TMS_BANUM_DIRCTRY {
	
	private int id;
	private String ba_no;
	private BigInteger mct;
	private String engine_no;
	private String chasis_no;
	private String status;
	private String old_ba_no;
	private String approved_by;
	private Date approve_date;
	private String creation_by;
	private Date creation_date;
	private String modify_by;
	private Date modify_date;
	private String deleted_by;
	private Date deleted_date;
	private int version_no;
	private String softdelete;
	private int parent_req_id;
	private String gift_flag;
	private String sus_no;
	private int prf_group;
	private String authority;
	private String veh_cat;
	private String qfd;
	private String  year_of_entry; 
	private String auction_amount;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBa_no() {
		return ba_no;
	}
	public void setBa_no(String ba_no) {
		this.ba_no = ba_no;
	}
	public BigInteger getMct() {
		return mct;
	}
	public void setMct(BigInteger mct) {
		this.mct = mct;
	}
	public String getEngine_no() {
		return engine_no;
	}
	public void setEngine_no(String engine_no) {
		this.engine_no = engine_no;
	}
	public String getChasis_no() {
		return chasis_no;
	}
	public void setChasis_no(String chasis_no) {
		this.chasis_no = chasis_no;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOld_ba_no() {
		return old_ba_no;
	}
	public void setOld_ba_no(String old_ba_no) {
		this.old_ba_no = old_ba_no;
	}
	public String getApproved_by() {
		return approved_by;
	}
	public void setApproved_by(String approved_by) {
		this.approved_by = approved_by;
	}
	public Date getApprove_date() {
		return approve_date;
	}
	public void setApprove_date(Date approve_date) {
		this.approve_date = approve_date;
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
	public String getDeleted_by() {
		return deleted_by;
	}
	public void setDeleted_by(String deleted_by) {
		this.deleted_by = deleted_by;
	}
	public Date getDeleted_date() {
		return deleted_date;
	}
	public void setDeleted_date(Date deleted_date) {
		this.deleted_date = deleted_date;
	}
	public int getVersion_no() {
		return version_no;
	}
	public void setVersion_no(int version_no) {
		this.version_no = version_no;
	}
	public String getSoftdelete() {
		return softdelete;
	}
	public void setSoftdelete(String softdelete) {
		this.softdelete = softdelete;
	}
	public int getParent_req_id() {
		return parent_req_id;
	}
	public void setParent_req_id(int parent_req_id) {
		this.parent_req_id = parent_req_id;
	}
	public String getGift_flag() {
		return gift_flag;
	}
	public void setGift_flag(String gift_flag) {
		this.gift_flag = gift_flag;
	}
	public String getSus_no() {
		return sus_no;
	}
	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}
	
	public int getPrf_group() {
		return prf_group;
	}
	public void setPrf_group(int prf_group) {
		this.prf_group = prf_group;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	public String getVeh_cat() {
		return veh_cat;
	}
	public void setVeh_cat(String veh_cat) {
		this.veh_cat = veh_cat;
	}
	public String getQfd() {
		return qfd;
	}
	public void setQfd(String qfd) {
		this.qfd = qfd;
	}
	public String getYear_of_entry() {
		return year_of_entry;
	}
	public void setYear_of_entry(String year_of_entry) {
		this.year_of_entry = year_of_entry;
	}
	public String getAuction_amount() {
		return auction_amount;
	}
	public void setAuction_amount(String auction_amount) {
		this.auction_amount = auction_amount;
	}

}
