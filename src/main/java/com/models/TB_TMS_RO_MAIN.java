package com.models;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.*;
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tb_tms_ro_main", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
public class TB_TMS_RO_MAIN {

	private int id;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private String ro_date;
	private String ro_no;

	public String getRo_no() {
		return ro_no;
	}

	public void setRo_no(String ro_no) {
		this.ro_no = ro_no;
	}

	private String type_of_release;
	public String getType_of_release() {
		return type_of_release;
	}

	public void setType_of_release(String type_of_release) {
		this.type_of_release = type_of_release;
	}

	private String sus_no;
	public String getSus_no() {
		return sus_no;
	}

	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date loan_frm_date;

	public Date getLoan_frm_date() {
		return loan_frm_date;
	}

	public void setLoan_frm_date(Date loan_frm_date) {
		this.loan_frm_date = loan_frm_date;
	}

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date loan_to_date;

	public Date getLoan_to_date() {
		return loan_to_date;
	}

	public void setLoan_to_date(Date loan_to_date) {
		this.loan_to_date = loan_to_date;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date_of_submission;

	public Date getDate_of_submission() {
		return date_of_submission;
	}

	public void setDate_of_submission(Date date_of_submission) {
		this.date_of_submission = date_of_submission;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date valid_upto;

	public Date getValid_upto() {
		return valid_upto;
	}

	public void setValid_upto(Date valid_upto) {
		this.valid_upto = valid_upto;
	}

	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private String aprv_rejec_remarks;

	public String getAprv_rejec_remarks() {
		return aprv_rejec_remarks;
	}

	public void setAprv_rejec_remarks(String aprv_rejec_remarks) {
		this.aprv_rejec_remarks = aprv_rejec_remarks;
	}

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date approve_date;

	public Date getApprove_date() {
		return approve_date;
	}

	public void setApprove_date(Date approve_date) {
		this.approve_date = approve_date;
	}

	private String creation_by;

	public String getCreation_by() {
		return creation_by;
	}

	public void setCreation_by(String creation_by) {
		this.creation_by = creation_by;
	}

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date creation_date;

	public Date getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date modify_by;

	public Date getModify_by() {
		return modify_by;
	}

	public void setModify_by(Date modify_by) {
		this.modify_by = modify_by;
	}

	private String deleted_by;

	public String getDeleted_by() {
		return deleted_by;
	}

	public void setDeleted_by(String deleted_by) {
		this.deleted_by = deleted_by;
	}

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date deleted_date;

	public Date getDeleted_date() {
		return deleted_date;
	}

	public void setDeleted_date(Date deleted_date) {
		this.deleted_date = deleted_date;
	}

	private int version_no;

	public int getVersion_no() {
		return version_no;
	}

	public void setVersion_no(int version_no) {
		this.version_no = version_no;
	}

	private String softdelete;

	public String getSoftdelete() {
		return softdelete;
	}

	public void setSoftdelete(String softdelete) {
		this.softdelete = softdelete;
	}

	private String filler_1;

	public String getFiller_1() {
		return filler_1;
	}

	public void setFiller_1(String filler_1) {
		this.filler_1 = filler_1;
	}

	private String filler_2;

	public String getFiller_2() {
		return filler_2;
	}

	public void setFiller_2(String filler_2) {
		this.filler_2 = filler_2;
	}

	private String filler_3;

	public String getFiller_3() {
		return filler_3;
	}

	public void setFiller_3(String filler_3) {
		this.filler_3 = filler_3;
	}

	private String type_of_ro;

	public String getType_of_ro() {
		return type_of_ro;
	}

	public void setType_of_ro(String type_of_ro) {
		this.type_of_ro = type_of_ro;
	}

	private String depot_sus_no;

	public String getDepot_sus_no() {
		return depot_sus_no;
	}

	public void setDepot_sus_no(String depot_sus_no) {
		this.depot_sus_no = depot_sus_no;
	}

	/*private String nrs;

	public String getNrs() {
		return nrs;
	}

	public void setNrs(String nrs) {
		this.nrs = nrs;
	}*/

	private String rio_no;

	public String getRio_no() {
		return rio_no;
	}

	public void setRio_no(String rio_no) {
		this.rio_no = rio_no;
	}

	private String issue_stock;

	public String getIssue_stock() {
		return issue_stock;
	}

	public void setIssue_stock(String issue_stock) {
		this.issue_stock = issue_stock;
	}

	private String other_issue_type;

	public String getOther_issue_type() {
		return other_issue_type;
	}

	public void setOther_issue_type(String other_issue_type) {
		this.other_issue_type = other_issue_type;
	}

	private String accounting;

	public String getAccounting() {
		return accounting;
	}

	public void setAccounting(String accounting) {
		this.accounting = accounting;
	}

	private String approved_by;

	public String getApproved_by() {
		return approved_by;
	}

	public void setApproved_by(String approved_by) {
		this.approved_by = approved_by;
	}

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date modify_date;

	public Date getModify_date() {
		return modify_date;
	}

	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}

	private String type_of_accounting;

	public String getType_of_accounting() {
		return type_of_accounting;
	}

	public void setType_of_accounting(String type_of_accounting) {
		this.type_of_accounting = type_of_accounting;
	}

	public String getRo_date() {
		return ro_date;
	}

	public void setRo_date(String ro_date) {
		this.ro_date = ro_date;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	private int quantity;

	//Added By Mitesh(22-10-2024)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date extended_on;

	public Date getExtended_on() {
		return extended_on;
	}

	public void setExtended_on(Date extended_on) {
		this.extended_on = extended_on;
	}
}