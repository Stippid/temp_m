package com.models.mnh;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tb_med_daily_bed_occupancy", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class Tb_Med_Daily_Bed_Occupancy {
	private int id;
	private int auth_beds;
	private int beds_laid_out;
	private int total_no_of_patients;
	private Double bed_occ_per_as_per_auth_bed;
	private Double bed_occ_per_as_per_laid_out_bed;
	private String created_by;
	private Date dt;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String sus_no;
	
	private int off_army;
	private int off_fam_army;
	private int jco_or_army;
	private int jco_or_fam_army;
	private int ex_off_army;
	private int ex_off_fam_army;
	private int ex_jco_or_army;
	private int ex_jco_or_fam_army;
	
	private int para_mil_pers;
	private int para_family;
	private int other_ne;
	private int other_family;
	
	private int cadet;
	private int rect_agniveer;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
  	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAuth_beds() {
		return auth_beds;
	}
	public void setAuth_beds(int auth_beds) {
		this.auth_beds = auth_beds;
	}
	public int getBeds_laid_out() {
		return beds_laid_out;
	}
	public void setBeds_laid_out(int beds_laid_out) {
		this.beds_laid_out = beds_laid_out;
	}
	public int getTotal_no_of_patients() {
		return total_no_of_patients;
	}
	public void setTotal_no_of_patients(int total_no_of_patients) {
		this.total_no_of_patients = total_no_of_patients;
	}
	public Double getBed_occ_per_as_per_auth_bed() {
		return bed_occ_per_as_per_auth_bed;
	}
	public void setBed_occ_per_as_per_auth_bed(Double bed_occ_per_as_per_auth_bed) {
		this.bed_occ_per_as_per_auth_bed = bed_occ_per_as_per_auth_bed;
	}
	public Double getBed_occ_per_as_per_laid_out_bed() {
		return bed_occ_per_as_per_laid_out_bed;
	}
	public void setBed_occ_per_as_per_laid_out_bed(Double bed_occ_per_as_per_laid_out_bed) {
		this.bed_occ_per_as_per_laid_out_bed = bed_occ_per_as_per_laid_out_bed;
	}
	@Column(length = 35)
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public Date getDt() {
		return dt;
	}
	public void setDt(Date dt) {
		this.dt = dt;
	}
	public Date getCreated_on() {
		return created_on;
	}
	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}
	@Column(length = 35)
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
	@Column(length = 8)
	public String getSus_no() {
		return sus_no;
	}
	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}
	public int getOff_army() {
		return off_army;
	}
	public void setOff_army(int off_army) {
		this.off_army = off_army;
	}
	public int getOff_fam_army() {
		return off_fam_army;
	}
	public void setOff_fam_army(int off_fam_army) {
		this.off_fam_army = off_fam_army;
	}
	public int getJco_or_army() {
		return jco_or_army;
	}
	public void setJco_or_army(int jco_or_army) {
		this.jco_or_army = jco_or_army;
	}
	public int getJco_or_fam_army() {
		return jco_or_fam_army;
	}
	public void setJco_or_fam_army(int jco_or_fam_army) {
		this.jco_or_fam_army = jco_or_fam_army;
	}
	public int getEx_off_army() {
		return ex_off_army;
	}
	public void setEx_off_army(int ex_off_army) {
		this.ex_off_army = ex_off_army;
	}
	public int getEx_off_fam_army() {
		return ex_off_fam_army;
	}
	public void setEx_off_fam_army(int ex_off_fam_army) {
		this.ex_off_fam_army = ex_off_fam_army;
	}
	public int getEx_jco_or_army() {
		return ex_jco_or_army;
	}
	public void setEx_jco_or_army(int ex_jco_or_army) {
		this.ex_jco_or_army = ex_jco_or_army;
	}
	public int getEx_jco_or_fam_army() {
		return ex_jco_or_fam_army;
	}
	public void setEx_jco_or_fam_army(int ex_jco_or_fam_army) {
		this.ex_jco_or_fam_army = ex_jco_or_fam_army;
	}
	public int getPara_mil_pers() {
		return para_mil_pers;
	}
	public void setPara_mil_pers(int para_mil_pers) {
		this.para_mil_pers = para_mil_pers;
	}
	public int getPara_family() {
		return para_family;
	}
	public void setPara_family(int para_family) {
		this.para_family = para_family;
	}
	public int getOther_ne() {
		return other_ne;
	}
	public void setOther_ne(int other_ne) {
		this.other_ne = other_ne;
	}
	public int getOther_family() {
		return other_family;
	}
	public void setOther_family(int other_family) {
		this.other_family = other_family;
	}
	public int getCadet() {
		return cadet;
	}
	public void setCadet(int cadet) {
		this.cadet = cadet;
	}
	public int getRect_agniveer() {
		return rect_agniveer;
	}
	public void setRect_agniveer(int rect_agniveer) {
		this.rect_agniveer = rect_agniveer;
	}
	

}