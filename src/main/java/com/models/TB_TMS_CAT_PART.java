/*package com.models;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "tb_tms_cat_part", uniqueConstraints = {
@UniqueConstraint(columnNames = "id")})
public class TB_TMS_CAT_PART {
	
	private int id;
	private int userid;
	private String  assembly_code;
	private String  cat_part_code ;
	private String cat_nomenclature ;
	private String creation_by;
	private Date creation_date;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getAssembly_code() {
		return assembly_code;
	}
	public void setAssembly_code(String assembly_code) {
		this.assembly_code = assembly_code;
	}
	public String getCat_part_code() {
		return cat_part_code;
	}
	public void setCat_part_code(String cat_part_code) {
		this.cat_part_code = cat_part_code;
	}
	public String getCat_nomenclature() {
		return cat_nomenclature;
	}
	public void setCat_nomenclature(String cat_nomenclature) {
		this.cat_nomenclature = cat_nomenclature;
	}
	public String getCreation_by() {
		return creation_by;
	}
	public void setCreation_by(String creation_by) {
		this.creation_by = creation_by;
	}
	public Date getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}
	
	

}
*/