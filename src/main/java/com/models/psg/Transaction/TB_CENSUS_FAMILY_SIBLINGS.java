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

import org.hibernate.annotations.ColumnTransformer;
import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name = "tb_psg_census_family_siblings", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
public class TB_CENSUS_FAMILY_SIBLINGS {
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

	
	
	private String aadhar_no;
	private String pan_no;
	

	private int sibling_service;
	private String sibling_personal_no;
	private String if_sibling_ser;
	private String other_sibling_ser;
	
	
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
	
	public BigInteger getComm_id() {
		return comm_id;
	}
	public void setComm_id(BigInteger comm_id) {
		this.comm_id = comm_id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
//	public String getType() {
//		return type;
//	}
//	public void setType(String type) {
//		this.type = type;
//	}
//	public String getAdoted() {
//		return adoted;
//	}
//	public void setAdoted(String adoted) {
//		this.adoted = adoted;
//	}
//	public Date getDate_of_adpoted() {
//		return date_of_adpoted;
//	}
//	public void setDate_of_adpoted(Date date_of_adpoted) {
//		this.date_of_adpoted = date_of_adpoted;
//	}
	

	public int getSibling_service() {
		return sibling_service;
	}
	public void setSibling_service(int sibling_service) {
		this.sibling_service = sibling_service;
	}
	public String getSibling_personal_no() {
		return sibling_personal_no;
	}
	public void setSibling_personal_no(String sibling_personal_no) {
		this.sibling_personal_no = sibling_personal_no;
	}
	public String getIf_sibling_ser() {
		return if_sibling_ser;
	}
	public void setIf_sibling_ser(String if_sibling_ser) {
		this.if_sibling_ser = if_sibling_ser;
	}
	public String getOther_sibling_ser() {
		return other_sibling_ser;
	}
	public void setOther_sibling_ser(String other_sibling_ser) {
		this.other_sibling_ser = other_sibling_ser;
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
