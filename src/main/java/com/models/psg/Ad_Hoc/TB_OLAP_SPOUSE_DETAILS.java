package com.models.psg.Ad_Hoc;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_olap_spouse_details", uniqueConstraints = { @UniqueConstraint(columnNames = "personal_no") })
public class TB_OLAP_SPOUSE_DETAILS {

	Date date_of_birth;
	String name;
	Date date_of_marriage;
	String nationality;
	Date date_of_divorce;
	String authority;
	Date date_of_authority;
	String marital_event;
	String marital_status;
	String place_of_birth;
	String pan_card;
	String spouse_service;
	String spouse_personal_no;
	String service_exservice;
	Date date_from_separated;
	Date date_to_separated;
	String aadhar_number;
	String personal_no;

	public Date getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate_of_marriage() {
		return date_of_marriage;
	}

	public void setDate_of_marriage(Date date_of_marriage) {
		this.date_of_marriage = date_of_marriage;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Date getDate_of_divorce() {
		return date_of_divorce;
	}

	public void setDate_of_divorce(Date date_of_divorce) {
		this.date_of_divorce = date_of_divorce;
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

	public String getMarital_event() {
		return marital_event;
	}

	public void setMarital_event(String marital_event) {
		this.marital_event = marital_event;
	}

	public String getMarital_status() {
		return marital_status;
	}

	public void setMarital_status(String marital_status) {
		this.marital_status = marital_status;
	}

	public String getPlace_of_birth() {
		return place_of_birth;
	}

	public void setPlace_of_birth(String place_of_birth) {
		this.place_of_birth = place_of_birth;
	}

	public String getPan_card() {
		return pan_card;
	}

	public void setPan_card(String pan_card) {
		this.pan_card = pan_card;
	}

	public String getSpouse_service() {
		return spouse_service;
	}

	public void setSpouse_service(String spouse_service) {
		this.spouse_service = spouse_service;
	}

	public String getSpouse_personal_no() {
		return spouse_personal_no;
	}

	public void setSpouse_personal_no(String spouse_personal_no) {
		this.spouse_personal_no = spouse_personal_no;
	}

	public String getService_exservice() {
		return service_exservice;
	}

	public void setService_exservice(String service_exservice) {
		this.service_exservice = service_exservice;
	}

	public Date getDate_from_separated() {
		return date_from_separated;
	}

	public void setDate_from_separated(Date date_from_separated) {
		this.date_from_separated = date_from_separated;
	}

	public Date getDate_to_separated() {
		return date_to_separated;
	}

	public void setDate_to_separated(Date date_to_separated) {
		this.date_to_separated = date_to_separated;
	}

	public String getAadhar_number() {
		return aadhar_number;
	}

	public void setAadhar_number(String aadhar_number) {
		this.aadhar_number = aadhar_number;
	}

	public String getPersonal_no() {
		return personal_no;
	}

	public void setPersonal_no(String personal_no) {
		this.personal_no = personal_no;
	}

}
