package com.models.psg.Transaction;





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

@Table(name = "tb_psg_census_family_divorce", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })

public class TB_CENSUS_FAMILY_DIVORCE {



	

	private int id;

	private int cen_id;

	private String divorced_spouse;

	@DateTimeFormat(pattern = "dd/MM/yyyy")

	private Date marriage_date;

	@DateTimeFormat(pattern = "dd/MM/yyyy")

	private Date divorce_date;

	private String created_by;

	private Date created_date;

	private String modified_by;

	private Date modified_date;

	

	

	

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

	public String getDivorced_spouse() {

		return divorced_spouse;

	}

	public void setDivorced_spouse(String divorced_spouse) {

		this.divorced_spouse = divorced_spouse;

	}

	public Date getMarriage_date() {

		return marriage_date;

	}

	public void setMarriage_date(Date marriage_date) {

		this.marriage_date = marriage_date;

	}

	public Date getDivorce_date() {

		return divorce_date;

	}

	public void setDivorce_date(Date divorce_date) {

		this.divorce_date = divorce_date;

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

	

	

}

