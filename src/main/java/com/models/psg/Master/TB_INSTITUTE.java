package com.models.psg.Master;



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

@Table(name = "tb_psg_mstr_institute", uniqueConstraints = { @UniqueConstraint(columnNames = "id"), })

public class TB_INSTITUTE {



	private int id;

	private int institute_id;	

	private String created_by;

	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date created_date;

	private String modify_by;

	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date modify_date;

	private int bn_id;

	private int company_id;

	private int platoon_id;
	
	private String status;

	

	@Id

	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)

	public int getId() {

		return id;

	}

	public void setId(int id) {

		this.id = id;

	}

	

	public int getInstitute_id() {

		return institute_id;

	}

	public void setInstitute_id(int institute_id) {

		this.institute_id = institute_id;

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

	public int getBn_id() {

		return bn_id;

	}

	public void setBn_id(int bn_id) {

		this.bn_id = bn_id;

	}

	public int getCompany_id() {

		return company_id;

	}

	public void setCompany_id(int company_id) {

		this.company_id = company_id;

	}

	

	public int getPlatoon_id() {

		return platoon_id;

	}

	public void setPlatoon_id(int platoon_id) {

		this.platoon_id = platoon_id;

	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	

}

