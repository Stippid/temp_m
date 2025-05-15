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
@Table(name = "tb_animal_adcr_dtls_history", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
public class TB_ANIMAL_ADCR_DTLS_HISTORY {

	private int id;
	private String name;
	private String gender;
	private String microchip_no;
	private String animal_type;
	private String army_no;
	private String sus_no;
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
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date date_of_admitted;
	private int census_id=0;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date ason_Date;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date approved_dt;
	private String approved_by;
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getMicrochip_no() {
		return microchip_no;
	}


	public void setMicrochip_no(String microchip_no) {
		this.microchip_no = microchip_no;
	}


	public String getAnimal_type() {
		return animal_type;
	}


	public void setAnimal_type(String animal_type) {
		this.animal_type = animal_type;
	}


	public String getArmy_no() {
		return army_no;
	}


	public void setArmy_no(String army_no) {
		this.army_no = army_no;
	}


	public String getSus_no() {
		return sus_no;
	}


	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}


	public Date getDate_of_birth() {
		return date_of_birth;
	}


	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
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


	public Date getDate_of_tos() {
		return date_of_tos;
	}


	public void setDate_of_tos(Date date_of_tos) {
		this.date_of_tos = date_of_tos;
	}


	public String getReject_remarks() {
		return reject_remarks;
	}


	public void setReject_remarks(String reject_remarks) {
		this.reject_remarks = reject_remarks;
	}


	public int getColor() {
		return color;
	}


	public void setColor(int color) {
		this.color = color;
	}


	public int getBreed() {
		return breed;
	}


	public void setBreed(int breed) {
		this.breed = breed;
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


	public int getFitness_status() {
		return fitness_status;
	}


	public void setFitness_status(int fitness_status) {
		this.fitness_status = fitness_status;
	}


	public int getType_of_dog() {
		return type_of_dog;
	}


	public void setType_of_dog(int type_of_dog) {
		this.type_of_dog = type_of_dog;
	}


	public Date getDate_of_admitted() {
		return date_of_admitted;
	}


	public void setDate_of_admitted(Date date_of_admitted) {
		this.date_of_admitted = date_of_admitted;
	}


	public int getCensus_id() {
		return census_id;
	}


	public void setCensus_id(int census_id) {
		this.census_id = census_id;
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
	
	
	
}
