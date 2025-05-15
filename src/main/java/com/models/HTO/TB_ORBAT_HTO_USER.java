package com.models.HTO;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_orbat_hto_user" , uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class TB_ORBAT_HTO_USER {

	private int id;
	private String login_name;
	public String user_name;
	private String sus_no;
	private String personnel_no;
	private String created_by;
	private Date created_date;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	
	
	public String getLogin_name() {
		return login_name;
	}


	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}


	public String getUser_name() {
		return user_name;
	}


	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}


	public String getSus_no() {
		return sus_no;
	}


	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}


	public String getPersonnel_no() {
		return personnel_no;
	}


	public void setPersonnel_no(String personnel_no) {
		this.personnel_no = personnel_no;
	}


	public void setId(int id) {
		this.id = id;
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

}
