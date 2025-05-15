package com.models;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_tms_mct_req_agency_master", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
public class TB_TMS_MCT_REG_AGENCY_MASTER {

	private int id;
	//private String req_agency;
	private String created_by;
	private String created_on;
	private String sus_no;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/*public String getReq_agency() {
		return req_agency;
	}

	public void setReq_agency(String req_agency) {
		this.req_agency = req_agency;
	}*/

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public String getCreated_on() {
		return created_on;
	}

	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}

	public String getSus_no() {
		return sus_no;
	}

	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}

}
