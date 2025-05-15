package com.model.Animal;

import static javax.persistence.GenerationType.IDENTITY;



import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "tb_animal_census_dtls", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })

public class TB_ANIMAL_CENSUS_DTLS {
	
	
	
	private int id;
	private String name;
	private String gender;
	private String microchip_no;
	private String animal_type;
	private String sire_name;
	private String sire_armyno;
	private String dam_name;
	private String dam_armyno;
	private String army_no;
	private String unit_sus_no;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date date_of_birth;
	private String created_by;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date created_date;
	private String modified_by;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date modified_date;
	private int status; 
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date date_of_tos;
	private String reject_remarks;
	private int color=0;
	private int breed=0;
	private int source=0;
	private int specialization=0;
	private int fitness_status=0;
	private int type_of_dog=0;
	private String front_img_path;
	private String right_img_path;
	private String left_img_path;
	private String auth_letter_no;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date date_of_auth;
	private String animal_purchase_cost="0";
	private String fitness_deployment;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date date_of_admitted;
	
	
	public Date getDate_of_admitted() {
		return date_of_admitted;
	}
	public void setDate_of_admitted(Date date_of_admitted) {
		this.date_of_admitted = date_of_admitted;
	}
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getGender() {
		return gender;
	}
	public String getMicrochip_no() {
		return microchip_no;
	}
	public String getAnimal_type() {
		return animal_type;
	}
	public String getSire_name() {
		return sire_name;
	}
	public String getSire_armyno() {
		return sire_armyno;
	}
	public String getDam_name() {
		return dam_name;
	}
	public String getDam_armyno() {
		return dam_armyno;
	}
	public String getArmy_no() {
		return army_no;
	}
	public String getUnit_sus_no() {
		return unit_sus_no;
	}
	public Date getDate_of_birth() {
		return date_of_birth;
	}
	public String getCreated_by() {
		return created_by;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public String getModified_by() {
		return modified_by;
	}
	public Date getModified_date() {
		return modified_date;
	}
	public int getStatus() {
		return status;
	}
	public Date getDate_of_tos() {
		return date_of_tos;
	}
	public String getReject_remarks() {
		return reject_remarks;
	}
	public int getColor() {
		return color;
	}
	public int getBreed() {
		return breed;
	}
	public int getSource() {
		return source;
	}
	public int getSpecialization() {
		return specialization;
	}
	public int getFitness_status() {
		return fitness_status;
	}
	public int getType_of_dog() {
		return type_of_dog;
	}
	
	public String getAuth_letter_no() {
		return auth_letter_no;
	}
	public Date getDate_of_auth() {
		return date_of_auth;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setMicrochip_no(String microchip_no) {
		this.microchip_no = microchip_no;
	}
	public void setAnimal_type(String animal_type) {
		this.animal_type = animal_type;
	}
	public void setSire_name(String sire_name) {
		this.sire_name = sire_name;
	}
	public void setSire_armyno(String sire_armyno) {
		this.sire_armyno = sire_armyno;
	}
	public void setDam_name(String dam_name) {
		this.dam_name = dam_name;
	}
	public void setDam_armyno(String dam_armyno) {
		this.dam_armyno = dam_armyno;
	}
	public void setArmy_no(String army_no) {
		this.army_no = army_no;
	}
	public void setUnit_sus_no(String unit_sus_no) {
		this.unit_sus_no = unit_sus_no;
	}
	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public void setDate_of_tos(Date date_of_tos) {
		this.date_of_tos = date_of_tos;
	}
	public void setReject_remarks(String reject_remarks) {
		this.reject_remarks = reject_remarks;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public void setBreed(int breed) {
		this.breed = breed;
	}
	public void setSource(int source) {
		this.source = source;
	}
	public void setSpecialization(int specialization) {
		this.specialization = specialization;
	}
	public void setFitness_status(int fitness_status) {
		this.fitness_status = fitness_status;
	}
	public void setType_of_dog(int type_of_dog) {
		this.type_of_dog = type_of_dog;
	}
	
	public void setAuth_letter_no(String auth_letter_no) {
		this.auth_letter_no = auth_letter_no;
	}
	public void setDate_of_auth(Date date_of_auth) {
		this.date_of_auth = date_of_auth;
	}
	public String getAnimal_purchase_cost() {
		return animal_purchase_cost;
	}
	public void setAnimal_purchase_cost(String animal_purchase_cost) {
		this.animal_purchase_cost = animal_purchase_cost;
	}
	public String getFitness_deployment() {
		return fitness_deployment;
	}
	public void setFitness_deployment(String fitness_deployment) {
		this.fitness_deployment = fitness_deployment;
	}
	public String getFront_img_path() {
		return front_img_path;
	}
	public void setFront_img_path(String front_img_path) {
		this.front_img_path = front_img_path;
	}
	public String getRight_img_path() {
		return right_img_path;
	}
	public void setRight_img_path(String right_img_path) {
		this.right_img_path = right_img_path;
	}
	public String getLeft_img_path() {
		return left_img_path;
	}
	public void setLeft_img_path(String left_img_path) {
		this.left_img_path = left_img_path;
	}
	
	

}
