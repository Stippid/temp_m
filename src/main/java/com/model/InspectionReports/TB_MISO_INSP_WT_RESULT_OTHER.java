package com.model.InspectionReports;


import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_miso_insp_wt_result_other", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class TB_MISO_INSP_WT_RESULT_OTHER {

	@Id
	@GeneratedValue
	private int id;
	private String sus_no;
	private String weapons;
	private String category;
	private String total_no;
	private String marks_men;
	private String first_class;
	private String standard_shot;
	private String failed;
	private String number_exempted;
	private String number_yeto_fire;
	private Integer status;
	private String year;	
	private Date created_date;
	private String created_by;
	private Date approved_date;
	private String approved_by;

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
	public String getWeapons() {
		return weapons;
	}
	public void setWeapons(String weapons) {
		this.weapons = weapons;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getTotal_no() {
		return total_no;
	}
	public void setTotal_no(String total_no) {
		this.total_no = total_no;
	}
	public String getMarks_men() {
		return marks_men;
	}
	public void setMarks_men(String marks_men) {
		this.marks_men = marks_men;
	}
	public String getFirst_class() {
		return first_class;
	}
	public void setFirst_class(String first_class) {
		this.first_class = first_class;
	}
	public String getStandard_shot() {
		return standard_shot;
	}
	public void setStandard_shot(String standard_shot) {
		this.standard_shot = standard_shot;
	}
	public String getFailed() {
		return failed;
	}
	public void setFailed(String failed) {
		this.failed = failed;
	}
	public String getNumber_exempted() {
		return number_exempted;
	}
	public void setNumber_exempted(String number_exempted) {
		this.number_exempted = number_exempted;
	}
	public String getNumber_yeto_fire() {
		return number_yeto_fire;
	}
	public void setNumber_yeto_fire(String number_yeto_fire) {
		this.number_yeto_fire = number_yeto_fire;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public Date getApproved_date() {
		return approved_date;
	}
	public void setApproved_date(Date approved_date) {
		this.approved_date = approved_date;
	}
	public String getApproved_by() {
		return approved_by;
	}
	public void setApproved_by(String approved_by) {
		this.approved_by = approved_by;
	}
	
}
