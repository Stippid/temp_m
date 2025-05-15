package com.models.assets;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_it_asset_genr_sanction_form", uniqueConstraints = {
@UniqueConstraint(columnNames = "id")})
public class TB_IT_ASSET_GENR_SANCTION_FORM {
	
	@Id
	@GeneratedValue
	private int id;
    private String unit_name;
    private String sus_no;
    private String ic_no;
    private String name;
    private String rank;
    private String dob;
    private String doc;
    private String dor;
    private String eligibility;
    private String type_of_asset;
    private String batch_id;
    private String conv_no;
    private Date conv_date;
	private String created_by;
	private Date created_date;
	private String approved_by;
	private Date approved_date;
	  private int status;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUnit_name() {
		return unit_name;
	}
	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}
	public String getSus_no() {
		return sus_no;
	}
	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}
	public String getIc_no() {
		return ic_no;
	}
	public void setIc_no(String ic_no) {
		this.ic_no = ic_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getDoc() {
		return doc;
	}
	public void setDoc(String doc) {
		this.doc = doc;
	}
	public String getDor() {
		return dor;
	}
	public void setDor(String dor) {
		this.dor = dor;
	}
	public String getEligibility() {
		return eligibility;
	}
	public void setEligibility(String eligibility) {
		this.eligibility = eligibility;
	}
	public String getType_of_asset() {
		return type_of_asset;
	}
	public void setType_of_asset(String type_of_asset) {
		this.type_of_asset = type_of_asset;
	}
	public String getConv_no() {
		return conv_no;
	}
	public void setConv_no(String conv_no) {
		this.conv_no = conv_no;
	}
	public Date getConv_date() {
		return conv_date;
	}
	public void setConv_date(Date conv_date) {
		this.conv_date = conv_date;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public String getApproved_by() {
		return approved_by;
	}
	public void setApproved_by(String approved_by) {
		this.approved_by = approved_by;
	}
	public Date getApproved_date() {
		return approved_date;
	}
	public void setApproved_date(Date approved_date) {
		this.approved_date = approved_date;
	}
	public String getBatch_id() {
		return batch_id;
	}
	public void setBatch_id(String batch_id) {
		this.batch_id = batch_id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	

	
	
}
