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
@Table(name = "tb_insp_up_gradation_report", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
public class TB_INSP_UP_GRADATION_REPORT {

	
	private int id;
	private String affected_up_gradation_class_iv;  
	private String affected_up_gradation_class_i;
	private String affected_up_gradation_class_ii;
	private String affected_up_gradation_class_iii;
	

	private String during_up_gradation_class_i;
	private String during_up_gradation_class_ii;
	private String during_up_gradation_class_iii;
	private String during_up_gradation_class_iv;
	
	
	private String total_available_class_i;
	private String total_available_class_ii;
	private String total_available_class_iii;
	private String total_available_class_iv;
	
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String status;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getAffected_up_gradation_class_i() {
		return affected_up_gradation_class_i;
	}

	public void setAffected_up_gradation_class_i(String affected_up_gradation_class_i) {
		this.affected_up_gradation_class_i = affected_up_gradation_class_i;
	}

	public String getAffected_up_gradation_class_ii() {
		return affected_up_gradation_class_ii;
	}

	public void setAffected_up_gradation_class_ii(String affected_up_gradation_class_ii) {
		this.affected_up_gradation_class_ii = affected_up_gradation_class_ii;
	}

	public String getAffected_up_gradation_class_iii() {
		return affected_up_gradation_class_iii;
	}

	public void setAffected_up_gradation_class_iii(String affected_up_gradation_class_iii) {
		this.affected_up_gradation_class_iii = affected_up_gradation_class_iii;
	}

	public String getAffected_up_gradation_class_iv() {
		return affected_up_gradation_class_iv;
	}

	public void setAffected_up_gradation_class_iv(String affected_up_gradation_class_iv) {
		this.affected_up_gradation_class_iv = affected_up_gradation_class_iv;
	}

	public String getDuring_up_gradation_class_i() {
		return during_up_gradation_class_i;
	}

	public void setDuring_up_gradation_class_i(String during_up_gradation_class_i) {
		this.during_up_gradation_class_i = during_up_gradation_class_i;
	}

	public String getDuring_up_gradation_class_ii() {
		return during_up_gradation_class_ii;
	}

	public void setDuring_up_gradation_class_ii(String during_up_gradation_class_ii) {
		this.during_up_gradation_class_ii = during_up_gradation_class_ii;
	}

	public String getDuring_up_gradation_class_iii() {
		return during_up_gradation_class_iii;
	}

	public void setDuring_up_gradation_class_iii(String during_up_gradation_class_iii) {
		this.during_up_gradation_class_iii = during_up_gradation_class_iii;
	}

	public String getDuring_up_gradation_class_iv() {
		return during_up_gradation_class_iv;
	}

	public void setDuring_up_gradation_class_iv(String during_up_gradation_class_iv) {
		this.during_up_gradation_class_iv = during_up_gradation_class_iv;
	}

	public String getTotal_available_class_i() {
		return total_available_class_i;
	}

	public void setTotal_available_class_i(String total_available_class_i) {
		this.total_available_class_i = total_available_class_i;
	}

	public String getTotal_available_class_ii() {
		return total_available_class_ii;
	}

	public void setTotal_available_class_ii(String total_available_class_ii) {
		this.total_available_class_ii = total_available_class_ii;
	}

	public String getTotal_available_class_iii() {
		return total_available_class_iii;
	}

	public void setTotal_available_class_iii(String total_available_class_iii) {
		this.total_available_class_iii = total_available_class_iii;
	}

	public String getTotal_available_class_iv() {
		return total_available_class_iv;
	}

	public void setTotal_available_class_iv(String total_available_class_iv) {
		this.total_available_class_iv = total_available_class_iv;
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
	
	public String getModified_by() {
		return modified_by;
	}

	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}

	public Date getModified_on() {
		return modified_on;
	}

	public void setModified_on(Date modified_on) {
		this.modified_on = modified_on;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
