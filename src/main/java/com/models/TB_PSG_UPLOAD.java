package com.models;

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
@Table(name = "tb_psg_upload", uniqueConstraints = { @UniqueConstraint(columnNames = "id"), })
public class TB_PSG_UPLOAD {

	private int id;
	private String sus_no;
	private String up_offrs_do_2_iaff_3010;
	private String up_str_return_iaff_3008;
	private String up_offrs__arrival_rp;
	private String up_str_return_iaff_3009_a_b;
	private String up_jco_do_2;
	private String up_statics_pers_cns;
	private String created_by;
	private String modified_by;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date created_date;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date modified_date;
	private String date;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSus_no() {
		return sus_no;
	}

	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}

	public String getUp_offrs_do_2_iaff_3010() {
		return up_offrs_do_2_iaff_3010;
	}

	public void setUp_offrs_do_2_iaff_3010(String up_offrs_do_2_iaff_3010) {
		this.up_offrs_do_2_iaff_3010 = up_offrs_do_2_iaff_3010;
	}

	public String getUp_str_return_iaff_3008() {
		return up_str_return_iaff_3008;
	}

	public void setUp_str_return_iaff_3008(String up_str_return_iaff_3008) {
		this.up_str_return_iaff_3008 = up_str_return_iaff_3008;
	}

	public String getUp_offrs__arrival_rp() {
		return up_offrs__arrival_rp;
	}

	public void setUp_offrs__arrival_rp(String up_offrs__arrival_rp) {
		this.up_offrs__arrival_rp = up_offrs__arrival_rp;
	}

	public String getUp_str_return_iaff_3009_a_b() {
		return up_str_return_iaff_3009_a_b;
	}

	public void setUp_str_return_iaff_3009_a_b(String up_str_return_iaff_3009_a_b) {
		this.up_str_return_iaff_3009_a_b = up_str_return_iaff_3009_a_b;
	}

	public String getUp_jco_do_2() {
		return up_jco_do_2;
	}

	public void setUp_jco_do_2(String up_jco_do_2) {
		this.up_jco_do_2 = up_jco_do_2;
	}

	public String getUp_statics_pers_cns() {
		return up_statics_pers_cns;
	}

	public void setUp_statics_pers_cns(String up_statics_pers_cns) {
		this.up_statics_pers_cns = up_statics_pers_cns;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public String getModified_by() {
		return modified_by;
	}

	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public Date getModified_date() {
		return modified_date;
	}

	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
