package com.model.Animal;

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
@Table(name = "tb_animal_award_history", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
public class TB_ANIMAL_AWARD_HISTORY {

	private int id;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date ason_Date;
	private int census_id=0;
	private int award=0;
	private String remarks;
	private String created_by;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date created_date;
	private String modified_by;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date modified_date;
	private int status=0;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date approved_dt;
	private String approved_by;
	private String sus_no;
	private String doc_path;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getAson_Date() {
		return ason_Date;
	}
	public void setAson_Date(Date ason_Date) {
		this.ason_Date = ason_Date;
	}
	public int getCensus_id() {
		return census_id;
	}
	public void setCensus_id(int census_id) {
		this.census_id = census_id;
	}
	public int getAward() {
		return award;
	}
	public void setAward(int award) {
		this.award = award;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getApproved_dt() {
		return approved_dt;
	}

	public void setApproved_dt(Date approved_dt) {
		this.approved_dt = approved_dt;
	}

	public String getApproved_by() {
		return approved_by;
	}

	public void setApproved_by(String approved_by) {
		this.approved_by = approved_by;
	}
	
	public String getSus_no() {
		return sus_no;
	}


	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}

	public String getDoc_path() {
		return doc_path;
	}
	public void setDoc_path(String doc_path) {
		this.doc_path = doc_path;
	}

}
