package com.models.psg.Transaction;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ColumnTransformer;
import org.springframework.format.annotation.DateTimeFormat;

import com.dao.Assets.interUnitTransf_DAO;

@Entity

@Table(name = "tb_psg_census_family_married", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })

public class TB_CENSUS_FAMILY_MRG {

	private int id;

	private int cen_id;

	private String maiden_name;



	private Date date_of_birth;

	private String place_of_birth;

	

	private Date marriage_date;

	private int nationality;

	

	private String adhar_number;

	private String created_by;

	private Date created_date;

	private String modified_by;

	private Date modified_date;

	@DateTimeFormat(pattern = "dd/MM/yyyy")

	private Date divorce_date;

	@DateTimeFormat(pattern = "dd/MM/yyyy")

	private Date date_of_authority;

	private String authority;

	private int type_of_event;

	private int marital_status;

	private BigInteger comm_id;

	private int status;

	private String pan_card;

	private String other_nationality;

	private int spouse_service;

	private String spouse_personal_no;

	private String if_spouse_ser;

	private String other_spouse_ser;

	private String reject_remarks;

	@DateTimeFormat(pattern = "dd/MM/yyyy")

	private Date separated_form_dt;

	@DateTimeFormat(pattern = "dd/MM/yyyy")

	private Date separated_to_dt;
	private int spouse_profession;
	private int spouse_arm_service;
	private int spouse_rank;
	

	public String getOther_spouse_ser() {

		return other_spouse_ser;

	}

	public void setOther_spouse_ser(String other_spouse_ser) {

		this.other_spouse_ser = other_spouse_ser;

	}

	public Date getDivorce_date() {

		return divorce_date;

	}

	public void setDivorce_date(Date divorce_date) {

		this.divorce_date = divorce_date;

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

	public int getCen_id() {

		return cen_id;

	}

	public void setCen_id(int cen_id) {

		this.cen_id = cen_id;

	}

	public String getMaiden_name() {

		return maiden_name;

	}

	public void setMaiden_name(String maiden_name) {

		this.maiden_name = maiden_name;

	}

	public Date getDate_of_birth() {

		return date_of_birth;

	}

	public void setDate_of_birth(Date date_of_birth) {

		this.date_of_birth = date_of_birth;

	}

	public String getPlace_of_birth() {

		return place_of_birth;

	}

	public void setPlace_of_birth(String place_of_birth) {

		this.place_of_birth = place_of_birth;

	}

	public Date getMarriage_date() {

		return marriage_date;

	}

	public void setMarriage_date(Date marriage_date) {

		this.marriage_date = marriage_date;

	}

	public int getNationality() {

		return nationality;

	}

	public void setNationality(int nationality) {

		this.nationality = nationality;

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

	public int getSpouse_service() {

		return spouse_service;

	}

	public void setSpouse_service(int spouse_service) {

		this.spouse_service = spouse_service;

	}

	public String getSpouse_personal_no() {

		return spouse_personal_no;

	}

	public void setSpouse_personal_no(String spouse_personal_no) {

		this.spouse_personal_no = spouse_personal_no;

	}

	public String getIf_spouse_ser() {

		return if_spouse_ser;

	}

	public void setIf_spouse_ser(String if_spouse_ser) {

		this.if_spouse_ser = if_spouse_ser;

	}

	public Date getDate_of_authority() {

		return date_of_authority;

	}

	public void setDate_of_authority(Date date_of_authority) {

		this.date_of_authority = date_of_authority;

	}

	public String getAuthority() {

		return authority;

	}

	public void setAuthority(String authority) {

		this.authority = authority;

	}

	public int getType_of_event() {

		return type_of_event;

	}

	public void setType_of_event(int type_of_event) {

		this.type_of_event = type_of_event;

	}

	
	public BigInteger getComm_id() {
		return comm_id;
	}

	public void setComm_id(BigInteger comm_id) {
		this.comm_id = comm_id;
	}

	public int getStatus() {

		return status;

	}

	public void setStatus(int status) {

		this.status = status;

	}

	public int getMarital_status() {

		return marital_status;

	}

	public void setMarital_status(int marital_status) {

		this.marital_status = marital_status;

	}





	public String getOther_nationality() {

		return other_nationality;

	}

	public void setOther_nationality(String other_nationality) {

		this.other_nationality = other_nationality;

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

	private int cancel_status=-1;

	private String cancel_by;

	private Date cancel_date;

	

	

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

	public String getReject_remarks() {

		return reject_remarks;

	}

	public void setReject_remarks(String reject_remarks) {

		this.reject_remarks = reject_remarks;

	}

	public Date getSeparated_form_dt() {

		return separated_form_dt;

	}

	public void setSeparated_form_dt(Date separated_form_dt) {

		this.separated_form_dt = separated_form_dt;

	}

	public Date getSeparated_to_dt() {

		return separated_to_dt;

	}

	public void setSeparated_to_dt(Date separated_to_dt) {

		this.separated_to_dt = separated_to_dt;

	}

	@Column
	@ColumnTransformer(
		    read =  "pgp_sym_decrypt(" +
		            "    pan_card::bytea, " +
		            "current_setting('miso.version')" +
		            ")",
		    write = "pgp_sym_encrypt( " +
		            "    ?, " +
		            "current_setting('miso.version')" +
		            ") "
		)
	public String getPan_card() {

		return pan_card;

	}

	public void setPan_card(String pan_card) {

		this.pan_card = pan_card;

	}

	@Column
	@ColumnTransformer(
		    read =  "pgp_sym_decrypt(" +
		            "    adhar_number::bytea, " +
		            "current_setting('miso.version')" +
		            ")",
		    write = "pgp_sym_encrypt( " +
		            "    ?, " +
		            "current_setting('miso.version')" +
		            ") "
		)
	public String getAdhar_number() {
		return adhar_number;
	}

	public void setAdhar_number(String adhar_number) {
		this.adhar_number = adhar_number;
	}

	public int getSpouse_profession() {
		return spouse_profession;
	}

	public void setSpouse_profession(int spouse_profession) {
		this.spouse_profession = spouse_profession;
	}

	public int getSpouse_arm_service() {
		return spouse_arm_service;
	}

	public void setSpouse_arm_service(int spouse_arm_service) {
		this.spouse_arm_service = spouse_arm_service;
	}

	public int getSpouse_rank() {
		return spouse_rank;
	}

	public void setSpouse_rank(int spouse_rank) {
		this.spouse_rank = spouse_rank;
	}
	

	

}

