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
@Entity
@Table(name = "tb_psg_census_detail_p", uniqueConstraints={ @UniqueConstraint(columnNames={"id"}), @UniqueConstraint(columnNames={"comm_id"})
})
public class TB_CENSUS_DETAIL_Parent {
	private int id;
	private BigInteger comm_id;
	private String first_name;
	private String middle_name;
	private String last_name;
	private String place_birth;
	private int district_birth;
	private int state_birth;
	private int country_birth;
	private int nationality;
	private int religion;
	private int marital_status;
	private int blood_group; 
	private int height;
	private String adhar_number;
	private String pancard_number;
	private String father_adhar_number;
	private String father_pancard_number;
	private String mother_adhar_number;
	private String mother_pancard_number;
	private String father_name;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date father_dob=null;
	private String father_place_birth;
	private int father_profession=0;
	private String father_service;
	private String father_other;
	private String father_personal_no;
	private String mother_name;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date mother_dob=null;
	private String mother_place_birth;
	private int mother_profession=0;
	private String mother_service;
	private String mother_other;
	private String mother_personal_no;
	private String mother_proffother;
	private String father_proffother;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private int status;
	private int mother_tongue;
	private int ncc_type;
	private int update_ofr_status;
	private int org_state;
	private int org_tehsil;
	private int org_country;
	private int org_district;
	private int com_institute;
	private int com_bn_id;
	private int com_company_id;
	private int com_platoon_id;
	private int training_institute;
	private int training_bn_id;
	private int training_company_id;
	private String org_domicile_country_other;
	private String org_domicile_state_other; 
	private String org_domicile_district_other;
	private String org_domicile_tehsil_other;
	private String religion_other;
	private String district_birth_other;
	private String state_birth_other;
	private String country_birth_other;
	private String nationality_other;
	private String mother_tongue_other;
	private int update_ofr_cancel_status;
	private String reject_remarks;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "comm_id", unique = true, nullable = false)
	public BigInteger getComm_id() {
		return comm_id;
	}
	public void setComm_id(BigInteger comm_id) {
		this.comm_id = comm_id;
	}
	/*public int getComm_id() {
		return comm_id;
	}
	public void setComm_id(int comm_id) {
		this.comm_id = comm_id;
	}*/
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getMiddle_name() {
		return middle_name;
	}
	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public int getDistrict_birth() {
		return district_birth;
	}
	public void setDistrict_birth(int district_birth) {
		this.district_birth = district_birth;
	}
	public int getState_birth() {
		return state_birth;
	}
	public void setState_birth(int state_birth) {
		this.state_birth = state_birth;
	}
	public int getNationality() {
		return nationality;
	}
	public void setNationality(int nationality) {
		this.nationality = nationality;
	}
	public int getCountry_birth() {
		return country_birth;
	}
	public void setCountry_birth(int country_birth) {
		this.country_birth = country_birth;
	}
	public int getReligion() {
		return religion;
	}
	public void setReligion(int religion) {
		this.religion = religion;
	}
	public int getMarital_status() {
		return marital_status;
	}
	public void setMarital_status(int marital_status) {
		this.marital_status = marital_status;
	}
	public int getBlood_group() {
		return blood_group;
	}
	public void setBlood_group(int blood_group) {
		this.blood_group = blood_group;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
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
	public String getFather_name() {
		return father_name;
	}
	public void setFather_name(String father_name) {
		this.father_name = father_name;
	}
	public Date getFather_dob() {
		return father_dob;
	}
	public void setFather_dob(Date father_dob) {
		this.father_dob = father_dob;
	}
	public String getFather_place_birth() {
		return father_place_birth;
	}
	public void setFather_place_birth(String father_place_birth) {
		this.father_place_birth = father_place_birth;
	}
	public String getMother_name() {
		return mother_name;
	}
	public void setMother_name(String mother_name) {
		this.mother_name = mother_name;
	}
	public Date getMother_dob() {
		return mother_dob;
	}
	public void setMother_dob(Date mother_dob) {
		this.mother_dob = mother_dob;
	}
	public String getMother_place_birth() {
		return mother_place_birth;
	}
	public void setMother_place_birth(String mother_place_birth) {
		this.mother_place_birth = mother_place_birth;
	}
	public int getFather_profession() {
		return father_profession;
	}
	public void setFather_profession(int father_profession) {
		this.father_profession = father_profession;
	}
	public int getMother_profession() {
		return mother_profession;
	}
	public void setMother_profession(int mother_profession) {
		this.mother_profession = mother_profession;
	}
	public String getPlace_birth() {
		return place_birth;
	}
	public void setPlace_birth(String place_birth) {
		this.place_birth = place_birth;
	}
	public int getMother_tongue() {
		return mother_tongue;
	}
	public void setMother_tongue(int mother_tongue) {
		this.mother_tongue = mother_tongue;
	}
	public int getNcc_type() {
		return ncc_type;
	}
	public void setNcc_type(int ncc_type) {
		this.ncc_type = ncc_type;
	}
	public int getUpdate_ofr_status() {
		return update_ofr_status;
	}
	public void setUpdate_ofr_status(int update_ofr_status) {
		this.update_ofr_status = update_ofr_status;
	}
	public int getOrg_state() {
		return org_state;
	}
	public void setOrg_state(int org_state) {
		this.org_state = org_state;
	}
	public int getOrg_tehsil() {
		return org_tehsil;
	}
	public void setOrg_tehsil(int org_tehsil) {
		this.org_tehsil = org_tehsil;
	}
	public int getOrg_country() {
		return org_country;
	}
	public void setOrg_country(int org_country) {
		this.org_country = org_country;
	}
	public int getOrg_district() {
		return org_district;
	}
	public void setOrg_district(int org_district) {
		this.org_district = org_district;
	}
	
	@Column
	@ColumnTransformer(
		    read =  "pgp_sym_decrypt(" +
		            "    pancard_number::bytea, " +
		            "current_setting('miso.version')" +
		            ")",
		    write = "pgp_sym_encrypt( " +
		            "    ?, " +
		            "current_setting('miso.version')" +
		            ") "
		)
	public String getPancard_number() {
		return pancard_number;
	}
	public void setPancard_number(String pancard_number) {
		this.pancard_number = pancard_number;
	}
	public int getCom_institute() {
		return com_institute;
	}
	public void setCom_institute(int com_institute) {
		this.com_institute = com_institute;
	}
	public int getCom_bn_id() {
		return com_bn_id;
	}
	public void setCom_bn_id(int com_bn_id) {
		this.com_bn_id = com_bn_id;
	}
	public int getCom_company_id() {
		return com_company_id;
	}
	public void setCom_company_id(int com_company_id) {
		this.com_company_id = com_company_id;
	}
	public int getCom_platoon_id() {
		return com_platoon_id;
	}
	public void setCom_platoon_id(int com_platoon_id) {
		this.com_platoon_id = com_platoon_id;
	}
	public int getTraining_institute() {
		return training_institute;
	}
	public void setTraining_institute(int training_institute) {
		this.training_institute = training_institute;
	}
	public int getTraining_bn_id() {
		return training_bn_id;
	}
	public void setTraining_bn_id(int training_bn_id) {
		this.training_bn_id = training_bn_id;
	}
	public int getTraining_company_id() {
		return training_company_id;
	}
	public void setTraining_company_id(int training_company_id) {
		this.training_company_id = training_company_id;
	}
	
	
	public String getFather_service() {
		return father_service;
	}
	public void setFather_service(String father_service) {
		this.father_service = father_service;
	}
	public String getFather_other() {
		return father_other;
	}
	public void setFather_other(String father_other) {
		this.father_other = father_other;
	}
	public String getFather_personal_no() {
		return father_personal_no;
	}
	public void setFather_personal_no(String father_personal_no) {
		this.father_personal_no = father_personal_no;
	}
	public String getMother_service() {
		return mother_service;
	}
	public void setMother_service(String mother_service) {
		this.mother_service = mother_service;
	}
	public String getMother_other() {
		return mother_other;
	}
	public void setMother_other(String mother_other) {
		this.mother_other = mother_other;
	}
	public String getMother_personal_no() {
		return mother_personal_no;
	}
	public void setMother_personal_no(String mother_personal_no) {
		this.mother_personal_no = mother_personal_no;
	}
	public String getOrg_domicile_state_other() {
		return org_domicile_state_other;
	}
	public void setOrg_domicile_state_other(String org_domicile_state_other) {
		this.org_domicile_state_other = org_domicile_state_other;
	}
	public String getOrg_domicile_district_other() {
		return org_domicile_district_other;
	}
	public void setOrg_domicile_district_other(String org_domicile_district_other) {
		this.org_domicile_district_other = org_domicile_district_other;
	}
	public String getOrg_domicile_tehsil_other() {
		return org_domicile_tehsil_other;
	}
	public void setOrg_domicile_tehsil_other(String org_domicile_tehsil_other) {
		this.org_domicile_tehsil_other = org_domicile_tehsil_other;
	}
	public String getOrg_domicile_country_other() {
		return org_domicile_country_other;
	}
	public void setOrg_domicile_country_other(String org_domicile_country_other) {
		this.org_domicile_country_other = org_domicile_country_other;
	}
	public String getReligion_other() {
		return religion_other;
	}
	public void setReligion_other(String religion_other) {
		this.religion_other = religion_other;
	}
	public String getDistrict_birth_other() {
		return district_birth_other;
	}
	public void setDistrict_birth_other(String district_birth_other) {
		this.district_birth_other = district_birth_other;
	}
	public String getState_birth_other() {
		return state_birth_other;
	}
	public void setState_birth_other(String state_birth_other) {
		this.state_birth_other = state_birth_other;
	}
	public String getCountry_birth_other() {
		return country_birth_other;
	}
	public void setCountry_birth_other(String country_birth_other) {
		this.country_birth_other = country_birth_other;
	}
	public String getNationality_other() {
		return nationality_other;
	}
	public void setNationality_other(String nationality_other) {
		this.nationality_other = nationality_other;
	}
	public String getMother_tongue_other() {
		return mother_tongue_other;
	}
	public void setMother_tongue_other(String mother_tongue_other) {
		this.mother_tongue_other = mother_tongue_other;
	}
	public String getReject_remarks() {
		return reject_remarks;
	}
	public void setReject_remarks(String reject_remarks) {
		this.reject_remarks = reject_remarks;
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
	
	@Column
	@ColumnTransformer(
		    read =  "pgp_sym_decrypt(" +
		            "    father_adhar_number::bytea, " +
		            "current_setting('miso.version')" +
		            ")",
		    write = "pgp_sym_encrypt( " +
		            "    ?, " +
		            "current_setting('miso.version')" +
		            ") "
		)
	public String getFather_adhar_number() {
		return father_adhar_number;
	}
	public void setFather_adhar_number(String father_adhar_number) {
		this.father_adhar_number = father_adhar_number;
	}
	
	@Column
	@ColumnTransformer(
		    read =  "pgp_sym_decrypt(" +
		            "    mother_adhar_number::bytea, " +
		            "current_setting('miso.version')" +
		            ")",
		    write = "pgp_sym_encrypt( " +
		            "    ?, " +
		            "current_setting('miso.version')" +
		            ") "
		)
	public String getMother_adhar_number() {
		return mother_adhar_number;
	}
	public void setMother_adhar_number(String mother_adhar_number) {
		this.mother_adhar_number = mother_adhar_number;
	}
	
	@Column
	@ColumnTransformer(
		    read =  "pgp_sym_decrypt(" +
		            "    father_pancard_number::bytea, " +
		            "current_setting('miso.version')" +
		            ")",
		    write = "pgp_sym_encrypt( " +
		            "    ?, " +
		            "current_setting('miso.version')" +
		            ") "
		)
	public String getFather_pancard_number() {
		return father_pancard_number;
	}
	public void setFather_pancard_number(String father_pancard_number) {
		this.father_pancard_number = father_pancard_number;
	}

	@Column
	@ColumnTransformer(
		    read =  "pgp_sym_decrypt(" +
		            "    mother_pancard_number::bytea, " +
		            "current_setting('miso.version')" +
		            ")",
		    write = "pgp_sym_encrypt( " +
		            "    ?, " +
		            "current_setting('miso.version')" +
		            ") "
		)
	public String getMother_pancard_number() {
		return mother_pancard_number;
	}
	public void setMother_pancard_number(String mother_pancard_number) {
		this.mother_pancard_number = mother_pancard_number;
	}

	public int getUpdate_ofr_cancel_status() {
		return update_ofr_cancel_status;
	}
	public void setUpdate_ofr_cancel_status(int update_ofr_cancel_status) {
		this.update_ofr_cancel_status = update_ofr_cancel_status;
	}
	public String getMother_proffother() {
		return mother_proffother;
	}
	public void setMother_proffother(String mother_proffother) {
		this.mother_proffother = mother_proffother;
	}
	public String getFather_proffother() {
		return father_proffother;
	}
	public void setFather_proffother(String father_proffother) {
		this.father_proffother = father_proffother;
	}
}
