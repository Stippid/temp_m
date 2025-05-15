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
@Table(name = "tb_tms_assembly_master", uniqueConstraints = {
@UniqueConstraint(columnNames = "id")})
public class TB_TMS_ASSEMBLY_MASTER {
	
	private int id;
	private int userid;
	private String  assembly_code;
	private String  assembly_name ;
	private BigInteger  mct ;
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
	public String getAssembly_name() {
		return assembly_name;
	}
	public void setAssembly_name(String assembly_name) {
		this.assembly_name = assembly_name;
	}
	public BigInteger getMct() {
		return mct;
	}
	public void setMct(BigInteger mct) {
		this.mct = mct;
	}
}
*/