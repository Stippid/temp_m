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
@Table(name = "tb_animal_employment_history", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
public class TB_ANIMAL_EMPLOYMENT_HISTORY {
	
	private int id;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date ason_Date;
	private int census_id=0;
	private int employment=0;
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
	private String emp_sus;
	private String emp_name;
	private String emp_no;
	private String loc;
	private int fit_status=0;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date date_of_admited;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date date_of_unfit;
	private String remarks;
	
	
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
	public String getEmp_sus() {
		return emp_sus;
	}
	public void setEmp_sus(String emp_sus) {
		this.emp_sus = emp_sus;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public int getEmployment() {
		return employment;
	}
	public void setEmployment(int employment) {
		this.employment = employment;
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
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}
	public int getFit_status() {
		return fit_status;
	}
	public void setFit_status(int fit_status) {
		this.fit_status = fit_status;
	}
	public Date getDate_of_admited() {
		return date_of_admited;
	}
	public void setDate_of_admited(Date date_of_admited) {
		this.date_of_admited = date_of_admited;
	}
	public Date getDate_of_unfit() {
		return date_of_unfit;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setDate_of_unfit(Date date_of_unfit) {
		this.date_of_unfit = date_of_unfit;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	

}
