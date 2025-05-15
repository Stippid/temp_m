package com.models.psg.update_census_data;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ColumnTransformer;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tb_psg_census_children", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
public class TB_CENSUS_CHILDREN {

	private int id;
	private int cen_id;
	private String name;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date date_of_birth;
	private int relationship;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private int status;
	private BigInteger comm_id;
	private String type;
	private String adoted;
	private String aadhar_no;
	private String pan_no;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date date_of_adpoted;
	private int child_service;
	private String child_personal_no;
	private String if_child_ser;
	private String other_child_ser;
	private String reject_remarks;
    
	private int cancel_status=-1;
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
	public int getChild_service() {
		return child_service;
	}
	public void setChild_service(int child_service) {
		this.child_service = child_service;
	}
	public String getChild_personal_no() {
		return child_personal_no;
	}
	public void setChild_personal_no(String child_personal_no) {
		this.child_personal_no = child_personal_no;
	}
	public String getIf_child_ser() {
		return if_child_ser;
	}
	public void setIf_child_ser(String if_child_ser) {
		this.if_child_ser = if_child_ser;
	}
	public String getOther_child_ser() {
		return other_child_ser;
	}
	public void setOther_child_ser(String other_child_ser) {
		this.other_child_ser = other_child_ser;
	}
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	public int getRelationship() {
		return relationship;
	}
	public void setRelationship(int relationship) {
		this.relationship = relationship;
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
	
	public BigInteger getComm_id() {
		return comm_id;
	}
	public void setComm_id(BigInteger comm_id) {
		this.comm_id = comm_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAdoted() {
		return adoted;
	}
	public void setAdoted(String adoted) {
		this.adoted = adoted;
	}

	@Column
	@ColumnTransformer(
		    read =  "pgp_sym_decrypt(" +
		            "    pan_no::bytea, " +
		            "current_setting('miso.version')" +
		            ")",
		    write = "pgp_sym_encrypt( " +
		            "    ?, " +
		            "current_setting('miso.version')" +
		            ") "
		)
	public String getPan_no() {
		return pan_no;
	}
	public void setPan_no(String pan_no) {
		this.pan_no = pan_no;
	}
	public Date getDate_of_adpoted() {
		return date_of_adpoted;
	}
	public void setDate_of_adpoted(Date date_of_adpoted) {
		this.date_of_adpoted = date_of_adpoted;
	}
	public String getReject_remarks() {
		return reject_remarks;
	}
	public void setReject_remarks(String reject_remarks) {
		this.reject_remarks = reject_remarks;
	}
	
	@Column
	@ColumnTransformer(
		    read =  "pgp_sym_decrypt(" +
		            "    aadhar_no::bytea, " +
		            "current_setting('miso.version')" +
		            ")",
		    write = "pgp_sym_encrypt( " +
		            "    ?, " +
		            "current_setting('miso.version')" +
		            ") "
		)
	public String getAadhar_no() {
		return aadhar_no;
	}
	public void setAadhar_no(String aadhar_no) {
		this.aadhar_no = aadhar_no;
	}
	
	

}
