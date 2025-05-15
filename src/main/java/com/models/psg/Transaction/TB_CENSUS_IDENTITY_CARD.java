package com.models.psg.Transaction;

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

@Table(name = "tb_psg_identity_card", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })

public class TB_CENSUS_IDENTITY_CARD {

	private int id;

	private int census_id;

	private BigInteger comm_id;

	private int status;

	private String id_card_no;

	private String hair_other;

	private String eye_other;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date Issue_dt;

	private String Issue_authority;

	private String id_marks;

	private int hair_colour;

	private int eye_colour;

	private Date created_date;

	private String created_by;

	private Date modified_date;

	private String modified_by;

	private String identity_image;

	private Date image_updated_date;
	private String reject_remarks;
	private String unit_sus_no;

	@Id

	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)

	public int getId() {

		return id;

	}

	public void setId(int id) {

		this.id = id;

	}

	public int getCensus_id() {

		return census_id;

	}

	public void setCensus_id(int census_id) {

		this.census_id = census_id;

	}

	public int getStatus() {

		return status;

	}

	public void setStatus(int status) {

		this.status = status;

	}

	public String getId_card_no() {

		return id_card_no;

	}

	public void setId_card_no(String id_card_no) {

		this.id_card_no = id_card_no;

	}

	public Date getIssue_dt() {

		return Issue_dt;

	}

	public void setIssue_dt(Date issue_dt) {

		Issue_dt = issue_dt;

	}

	public String getIssue_authority() {

		return Issue_authority;

	}

	public void setIssue_authority(String issue_authority) {

		Issue_authority = issue_authority;

	}

	public String getId_marks() {

		return id_marks;

	}

	public void setId_marks(String id_marks) {

		this.id_marks = id_marks;

	}

	public int getHair_colour() {

		return hair_colour;

	}

	public void setHair_colour(int hair_colour) {

		this.hair_colour = hair_colour;

	}

	public int getEye_colour() {

		return eye_colour;

	}

	public void setEye_colour(int eye_colour) {

		this.eye_colour = eye_colour;

	}

	public Date getCreated_date() {

		return created_date;

	}

	public void setCreated_date(Date created_date) {

		this.created_date = created_date;

	}

	public String getCreated_by() {

		return created_by;

	}

	public void setCreated_by(String created_by) {

		this.created_by = created_by;

	}

	public Date getModified_date() {

		return modified_date;

	}

	public void setModified_date(Date modified_date) {

		this.modified_date = modified_date;

	}

	public String getModified_by() {

		return modified_by;

	}

	public void setModified_by(String modified_by) {

		this.modified_by = modified_by;

	}

	public String getIdentity_image() {

		return identity_image;

	}

	public void setIdentity_image(String identity_image) {

		this.identity_image = identity_image;

	}

	public Date getImage_updated_date() {

		return image_updated_date;

	}

	public void setImage_updated_date(Date image_updated_date) {

		this.image_updated_date = image_updated_date;

	}

	public String getHair_other() {
		return hair_other;
	}

	public void setHair_other(String hair_other) {
		this.hair_other = hair_other;
	}

	public String getEye_other() {
		return eye_other;
	}

	public void setEye_other(String eye_other) {
		this.eye_other = eye_other;
	}

	private int cancel_status = -1;
	private String cancel_by;
	private Date cancel_date;

	public int getCancel_status() {
		return cancel_status;
	}

	public void setCancel_status(int cancel_status) {
		this.cancel_status = cancel_status;
	}

	public String getCancel_by() {
		return cancel_by;
	}

	public void setCancel_by(String cancel_by) {
		this.cancel_by = cancel_by;
	}

	public Date getCancel_date() {
		return cancel_date;
	}

	public void setCancel_date(Date cancel_date) {
		this.cancel_date = cancel_date;
	}

	public String getReject_remarks() {
		return reject_remarks;
	}

	public void setReject_remarks(String reject_remarks) {
		this.reject_remarks = reject_remarks;
	}

	public BigInteger getComm_id() {
		return comm_id;
	}

	public void setComm_id(BigInteger comm_id) {
		this.comm_id = comm_id;
	}

	public String getUnit_sus_no() {
		return unit_sus_no;
	}

	public void setUnit_sus_no(String unit_sus_no) {
		this.unit_sus_no = unit_sus_no;
	}

}
