package com.models.psg.Ad_Hoc;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_olap_medical_details", uniqueConstraints = { @UniqueConstraint(columnNames = "personal_no") })
public class TB_OLAP_MEDICAL_DETAILS {

	String authority;
	Date date_of_authority;
	String shape;
	String shape_status;
	String shape_value;
	Date date_from;
	Date date_to;
	String diagnosis;
	String diagnosis_cause;
	String diagnosis_code;
	String clasification;
	String shape_sub_value;
	String diagnosis_1bx;
	Date date_from_1bx;
	Date date_to_1bx;
	String personal_no;
	
	
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
	public String getShape() {
		return shape;
	}
	public void setShape(String shape) {
		this.shape = shape;
	}
	public String getShape_status() {
		return shape_status;
	}
	public void setShape_status(String shape_status) {
		this.shape_status = shape_status;
	}
	public String getShape_value() {
		return shape_value;
	}
	public void setShape_value(String shape_value) {
		this.shape_value = shape_value;
	}
	public Date getDate_from() {
		return date_from;
	}
	public void setDate_from(Date date_from) {
		this.date_from = date_from;
	}
	public Date getDate_to() {
		return date_to;
	}
	public void setDate_to(Date date_to) {
		this.date_to = date_to;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public String getDiagnosis_cause() {
		return diagnosis_cause;
	}
	public void setDiagnosis_cause(String diagnosis_cause) {
		this.diagnosis_cause = diagnosis_cause;
	}
	public String getDiagnosis_code() {
		return diagnosis_code;
	}
	public void setDiagnosis_code(String diagnosis_code) {
		this.diagnosis_code = diagnosis_code;
	}
	public String getClasification() {
		return clasification;
	}
	public void setClasification(String clasification) {
		this.clasification = clasification;
	}
	public String getShape_sub_value() {
		return shape_sub_value;
	}
	public void setShape_sub_value(String shape_sub_value) {
		this.shape_sub_value = shape_sub_value;
	}
	public String getDiagnosis_1bx() {
		return diagnosis_1bx;
	}
	public void setDiagnosis_1bx(String diagnosis_1bx) {
		this.diagnosis_1bx = diagnosis_1bx;
	}
	public Date getDate_from_1bx() {
		return date_from_1bx;
	}
	public void setDate_from_1bx(Date date_from_1bx) {
		this.date_from_1bx = date_from_1bx;
	}
	public Date getDate_to_1bx() {
		return date_to_1bx;
	}
	public void setDate_to_1bx(Date date_to_1bx) {
		this.date_to_1bx = date_to_1bx;
	}
	public String getPersonal_no() {
		return personal_no;
	}
	public void setPersonal_no(String personal_no) {
		this.personal_no = personal_no;
	}
	
	
	

}
