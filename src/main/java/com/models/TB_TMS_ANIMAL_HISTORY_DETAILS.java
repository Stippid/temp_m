package com.models;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_tms_animal_details_tbl_history", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })

public class TB_TMS_ANIMAL_HISTORY_DETAILS  {

	private int id;
	private String army_num;
	private String sex;
	private String date_of_birth;
	private String image;
	private String microchip_no;
	private String details_of_sire;
	private String details_of_dam;
	private String date_of_death;
	private String medals_desc_details;
	private String unit_where_awarded;
	private String authority;
	private String award_date;
	private String remount_no;
	private String anml_type;
	private String sus_no;
	//private String unit_name;
	private String animal_purchase_cost;
	private String animal_present_cost;
	private String age;

	private String tos;
	private String tors;
	private String sos;
	private String sors;
	private String date_admitted;
	private int year;
	private String created_by;
	private Date created_date;
	private String modify_by;
	private Date modify_date;
	private String awared_remarks;
	private String disposal_remarks; 
	private String fitness_deployment;
	private int specialization;
	private int fitness_status;
	private int type_equines;
	private String name_of_dog;
	private int breed;
	private int colour;
	private int type_dog;
	private int source;
	private String disposal;
	private String issue_to_unit_name;
	private String disptrans;
	private String comd_sus_no;
	//private String comd_unit_name;
	private String dis_date;
	private String issue_date;
	private String record_status;

	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public String getSus_no() {
		return sus_no;
	}

	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}

	/*public String getUnit_name() {
		return unit_name;
	}

	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}*/

	public String getAnimal_purchase_cost() {
		return animal_purchase_cost;
	}

	public void setAnimal_purchase_cost(String animal_purchase_cost) {
		this.animal_purchase_cost = animal_purchase_cost;
	}

	public String getAnimal_present_cost() {
		return animal_present_cost;
	}

	public void setAnimal_present_cost(String animal_present_cost) {
		this.animal_present_cost = animal_present_cost;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getArmy_num() {
		return army_num;
	}

	public void setArmy_num(String army_num) {
		this.army_num = army_num;
	}

	

	
	

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}



	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getMicrochip_no() {
		return microchip_no;
	}

	public void setMicrochip_no(String microchip_no) {
		this.microchip_no = microchip_no;
	}

	public String getDetails_of_sire() {
		return details_of_sire;
	}

	public void setDetails_of_sire(String details_of_sire) {
		this.details_of_sire = details_of_sire;
	}

	public String getDetails_of_dam() {
		return details_of_dam;
	}

	public void setDetails_of_dam(String details_of_dam) {
		this.details_of_dam = details_of_dam;
	}

	public String getDate_of_death() {
		return date_of_death;
	}

	public void setDate_of_death(String date_of_death) {
		this.date_of_death = date_of_death;
	}

	

	public String getMedals_desc_details() {
		return medals_desc_details;
	}

	public void setMedals_desc_details(String medals_desc_details) {
		this.medals_desc_details = medals_desc_details;
	}

	public String getUnit_where_awarded() {
		return unit_where_awarded;
	}

	public void setUnit_where_awarded(String unit_where_awarded) {
		this.unit_where_awarded = unit_where_awarded;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getAward_date() {
		return award_date;
	}

	public void setAward_date(String award_date) {
		this.award_date = award_date;
	}


	public String getRemount_no() {
		return remount_no;
	}

	public void setRemount_no(String remount_no) {
		this.remount_no = remount_no;
	}

	public String getAnml_type() {
		return anml_type;
	}

	public void setAnml_type(String anml_type) {
		this.anml_type = anml_type;
	}

	
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getTos() {
		return tos;
	}

	public void setTos(String tos) {
		this.tos = tos;
	}

	public String getTors() {
		return tors;
	}

	public void setTors(String tors) {
		this.tors = tors;
	}

	public String getSos() {
		return sos;
	}

	public void setSos(String sos) {
		this.sos = sos;
	}

	public String getSors() {
		return sors;
	}

	public void setSors(String sors) {
		this.sors = sors;
	}



	public String getDate_admitted() {
		return date_admitted;
	}

	public void setDate_admitted(String date_admitted) {
		this.date_admitted = date_admitted;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
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

	public String getModify_by() {
		return modify_by;
	}

	public void setModify_by(String modify_by) {
		this.modify_by = modify_by;
	}

	public Date getModify_date() {
		return modify_date;
	}

	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}

	public String getAwared_remarks() {
		return awared_remarks;
	}

	public void setAwared_remarks(String awared_remarks) {
		this.awared_remarks = awared_remarks;
	}

	public String getDisposal_remarks() {
		return disposal_remarks;
	}

	public void setDisposal_remarks(String disposal_remarks) {
		this.disposal_remarks = disposal_remarks;
	}

	public String getFitness_deployment() {
		return fitness_deployment;
	}

	public void setFitness_deployment(String fitness_deployment) {
		this.fitness_deployment = fitness_deployment;
	}


	public int getFitness_status() {
		return fitness_status;
	}

	public void setFitness_status(int fitness_status) {
		this.fitness_status = fitness_status;
	}

	public int getType_equines() {
		return type_equines;
	}

	public void setType_equines(int type_equines) {
		this.type_equines = type_equines;
	}


	public int getBreed() {
		return breed;
	}

	public void setBreed(int breed) {
		this.breed = breed;
	}

	public int getColour() {
		return colour;
	}

	public void setColour(int colour) {
		this.colour = colour;
	}

	public String getName_of_dog() {
		return name_of_dog;
	}

	public void setName_of_dog(String name_of_dog) {
		this.name_of_dog = name_of_dog;
	}

	public int getType_dog() {
		return type_dog;
	}

	public void setType_dog(int type_dog) {
		this.type_dog = type_dog;
	}

	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public int getSpecialization() {
		return specialization;
	}

	public void setSpecialization(int specialization) {
		this.specialization = specialization;
	}

	public String getDisposal() {
		return disposal;
	}

	public void setDisposal(String disposal) {
		this.disposal = disposal;
	}

	public String getIssue_to_unit_name() {
		return issue_to_unit_name;
	}

	public void setIssue_to_unit_name(String issue_to_unit_name) {
		this.issue_to_unit_name = issue_to_unit_name;
	}

	public String getDisptrans() {
		return disptrans;
	}

	public void setDisptrans(String disptrans) {
		this.disptrans = disptrans;
	}

	public String getComd_sus_no() {
		return comd_sus_no;
	}

	public void setComd_sus_no(String comd_sus_no) {
		this.comd_sus_no = comd_sus_no;
	}

	/*public String getComd_unit_name() {
		return comd_unit_name;
	}

	public void setComd_unit_name(String comd_unit_name) {
		this.comd_unit_name = comd_unit_name;
	}*/

	public String getDis_date() {
		return dis_date;
	}

	public void setDis_date(String dis_date) {
		this.dis_date = dis_date;
	}

	public String getIssue_date() {
		return issue_date;
	}

	public void setIssue_date(String issue_date) {
		this.issue_date = issue_date;
	}

	public String getRecord_status() {
		return record_status;
	}

	public void setRecord_status(String record_status) {
		this.record_status = record_status;
	}

	



}
