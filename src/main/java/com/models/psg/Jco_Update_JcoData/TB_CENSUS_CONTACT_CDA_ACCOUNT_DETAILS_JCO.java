package com.models.psg.Jco_Update_JcoData;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.math.BigInteger;
import java.util.Date;

import org.hibernate.annotations.ColumnTransformer;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_psg_census_contact_cda_account_details_jco", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id"), })

public class TB_CENSUS_CONTACT_CDA_ACCOUNT_DETAILS_JCO {

	private int id;
	private int jco_id;
	private String created_by;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date created_date;
	private String gmail;
	private String mobile_no;
	private String modified_by;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date modified_date;
	private String nic_mail;
	private int status;
	private int cda_account_no;
	private int cancel_status = -1;
	private String cancel_by;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date cancel_date;
	private String initiated_from;
	private String reject_remarks;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getJco_id() {
		return jco_id;
	}

	public void setJco_id(int jco_id) {
		this.jco_id = jco_id;
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

	@Column
	@ColumnTransformer(
		    read =  "pgp_sym_decrypt(" +
		            "    gmail::bytea, " +
		            "current_setting('miso.version')" +
		            ")",
		    write = "pgp_sym_encrypt( " +
		            "    ?, " +
		            "current_setting('miso.version')" +
		            ") "
		)
	public String getGmail() {
		return gmail;
	}

	public void setGmail(String gmail) {
		this.gmail = gmail;
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
		            "    nic_mail::bytea, " +
		            "current_setting('miso.version')" +
		            ")",
		    write = "pgp_sym_encrypt( " +
		            "    ?, " +
		            "current_setting('miso.version')" +
		            ") "
		)
	public String getNic_mail() {
		return nic_mail;
	}

	public void setNic_mail(String nic_mail) {
		this.nic_mail = nic_mail;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCda_account_no() {
		return cda_account_no;
	}

	public void setCda_account_no(int cda_account_no) {
		this.cda_account_no = cda_account_no;
	}

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

	public String getInitiated_from() {
		return initiated_from;
	}

	public void setInitiated_from(String initiated_from) {
		this.initiated_from = initiated_from;
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
		            "    mobile_no::bytea, " +
		            "current_setting('miso.version')" +
		            ")",
		    write = "pgp_sym_encrypt( " +
		            "    ?, " +
		            "current_setting('miso.version')" +
		            ") "
		)
	public String getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}

}
