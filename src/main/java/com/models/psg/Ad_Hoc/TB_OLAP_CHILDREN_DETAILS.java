package com.models.psg.Ad_Hoc;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_olap_children_details", uniqueConstraints = { @UniqueConstraint(columnNames = "personal_no") })
public class TB_OLAP_CHILDREN_DETAILS {

	Date date_of_birth;
	String relationship;
	String specially_abled_child;
	String adopted_child;
	String pan_no;
	Date date_of_adpoted;
	String name;
	String child_service;
	String child_personal_no;
	String service_exservice;
	String aadhar_no;
	String personal_no;

	public Date getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getSpecially_abled_child() {
		return specially_abled_child;
	}

	public void setSpecially_abled_child(String specially_abled_child) {
		this.specially_abled_child = specially_abled_child;
	}

	public String getAdopted_child() {
		return adopted_child;
	}

	public void setAdopted_child(String adopted_child) {
		this.adopted_child = adopted_child;
	}

	public String getPan_no() {
		return pan_no;
	}

	public void setPan_no(String pan_no) {
		this.pan_no = pan_no;
	}

	public Date getDate_of_adpoted() {
		return date_of_adpoted;
	}

	public void setDate_of_adpoted(Date date_of_adpoted) {
		this.date_of_adpoted = date_of_adpoted;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getChild_service() {
		return child_service;
	}

	public void setChild_service(String child_service) {
		this.child_service = child_service;
	}

	public String getChild_personal_no() {
		return child_personal_no;
	}

	public void setChild_personal_no(String child_personal_no) {
		this.child_personal_no = child_personal_no;
	}

	public String getService_exservice() {
		return service_exservice;
	}

	public void setService_exservice(String service_exservice) {
		this.service_exservice = service_exservice;
	}

	public String getAadhar_no() {
		return aadhar_no;
	}

	public void setAadhar_no(String aadhar_no) {
		this.aadhar_no = aadhar_no;
	}

	public String getPersonal_no() {
		return personal_no;
	}

	public void setPersonal_no(String personal_no) {
		this.personal_no = personal_no;
	}

}
