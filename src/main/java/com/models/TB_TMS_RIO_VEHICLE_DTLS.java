package com.models;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigInteger;
import java.util.*;
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tb_tms_rio_vehicle_dtls" , uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class TB_TMS_RIO_VEHICLE_DTLS {
	
	private int id;
	private String rio_no;
	private BigInteger mct;
	private int quantity;
	private String inliuemct;
	private Date creation_date;
	private String modify_by;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date modify_date;
	private int version_no;
	private String softdelete;
	private String class_vehicle;
	private int remaining_quantity;
	private String sus_no;
	private String depot_sus_no;
	private String command_sus_no;
	//private String nrs;
	private String std_nomclature;
	private String type_of_release;
	private String accounting;
	private String issue_precedence;
	private String type_of_ro;
	private String issue_stock;
	private String ro_no;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date issue_date;
	private String ci_no;
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date valid_upto;
	private String status;
	private String rio_no_temp;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date approved_date;
	private String other_issue_type;
	private String amnd_extnd_remrks;
	private String conditional_remarks;
	private String remarks;
	private String type_of_issue;
	private int loan_duration;
	private String loan_auth;
	private String quantity_status;
	private String ba_no;
	private String type_of_vehicle;
	private String corps_sus_no;

	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getRio_no()
	{
		return rio_no;
	}
	public void setRio_no(String rio_no)
	{
		this.rio_no = rio_no;
	}

	public BigInteger getMct()
	{
		return mct;
	}
	public void setMct(BigInteger mct)
	{
		this.mct = mct;
	}

	public int getQuantity()
	{
		return quantity;
	}
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	public String getInliuemct()
	{
		return inliuemct;
	}
	public void setInliuemct(String inliuemct)
	{
		this.inliuemct = inliuemct;
	}
	
	public Date getCreation_date()
	{
		return creation_date;
	}
	public void setCreation_date(Date creation_date)
	{
		this.creation_date = creation_date;
	}

	public String getModify_by()
	{
		return modify_by;
	}
	public void setModify_by(String modify_by)
	{
		this.modify_by = modify_by;
	}
	
	public Date getModify_date()
	{
		return modify_date;
	}
	public void setModify_date(Date modify_date)
	{
		this.modify_date = modify_date;
	}

	public int getVersion_no()
	{
		return version_no;
	}
	public void setVersion_no(int version_no)
	{
		this.version_no = version_no;
	}
	
	public String getSoftdelete()
	{
		return softdelete;
	}
	public void setSoftdelete(String softdelete)
	{
		this.softdelete = softdelete;
	}

	
	
	public String getClass_vehicle()
	{
		return class_vehicle;
	}
	public void setClass_vehicle(String class_vehicle)
	{
		this.class_vehicle = class_vehicle;
	}

	
	
	public int getRemaining_quantity()
	{
		return remaining_quantity;
	}
	public void setRemaining_quantity(int remaining_quantity)
	{
		this.remaining_quantity = remaining_quantity;
	}

	
	
	public String getSus_no()
	{
		return sus_no;
	}
	public void setSus_no(String sus_no)
	{
		this.sus_no = sus_no;
	}

	
	
	public String getDepot_sus_no()
	{
		return depot_sus_no;
	}
	public void setDepot_sus_no(String depot_sus_no)
	{
		this.depot_sus_no = depot_sus_no;
	}

	
	
	public String getCommand_sus_no()
	{
		return command_sus_no;
	}
	public void setCommand_sus_no(String command_sus_no)
	{
		this.command_sus_no = command_sus_no;
	}

	
	/*public String getNrs()
	{
		return nrs;
	}
	public void setNrs(String nrs)
	{
		this.nrs = nrs;
	}*/


	
	
	public String getStd_nomclature()
	{
		return std_nomclature;
	}
	public void setStd_nomclature(String std_nomclature)
	{
		this.std_nomclature = std_nomclature;
	}

	
	
	public String getType_of_release()
	{
		return type_of_release;
	}
	public void setType_of_release(String type_of_release)
	{
		this.type_of_release = type_of_release;
	}

	
	
	public String getAccounting()
	{
		return accounting;
	}
	public void setAccounting(String accounting)
	{
		this.accounting = accounting;
	}

	
	
	public String getIssue_precedence()
	{
		return issue_precedence;
	}
	public void setIssue_precedence(String issue_precedence)
	{
		this.issue_precedence = issue_precedence;
	}

	
	
	public String getType_of_ro()
	{
		return type_of_ro;
	}
	public void setType_of_ro(String type_of_ro)
	{
		this.type_of_ro = type_of_ro;
	}

	
	
	public String getIssue_stock()
	{
		return issue_stock;
	}
	public void setIssue_stock(String issue_stock)
	{
		this.issue_stock = issue_stock;
	}

	
	
	public String getRo_no()
	{
		return ro_no;
	}
	public void setRo_no(String ro_no)
	{
		this.ro_no = ro_no;
	}

	
	
	public Date getIssue_date()
	{
		return issue_date;
	}
	public void setIssue_date(Date issue_date)
	{
		this.issue_date = issue_date;
	}


	
	public String getCi_no()
	{
		return ci_no;
	}
	public void setCi_no(String ci_no)
	{
		this.ci_no = ci_no;
	}


	
	public Date getValid_upto()
	{
		return valid_upto;
	}
	public void setValid_upto(Date valid_upto)
	{
		this.valid_upto = valid_upto;
	}

	
	public String getStatus()
	{
		return status;
	}
	public void setStatus(String status)
	{
		this.status = status;
	}

	
	
	public String getRio_no_temp()
	{
		return rio_no_temp;
	}
	public void setRio_no_temp(String rio_no_temp)
	{
		this.rio_no_temp = rio_no_temp;
	}

	
	public Date getApproved_date()
	{
		return approved_date;
	}
	public void setApproved_date(Date approved_date)
	{
		this.approved_date = approved_date;
	}


	
	
	public String getOther_issue_type()
	{
		return other_issue_type;
	}
	public void setOther_issue_type(String other_issue_type)
	{
		this.other_issue_type = other_issue_type;
	}

	

	public String getAmnd_extnd_remrks()
	{
		return amnd_extnd_remrks;
	}
	public void setAmnd_extnd_remrks(String amnd_extnd_remrks)
	{
		this.amnd_extnd_remrks = amnd_extnd_remrks;
	}


	
	public String getConditional_remarks()
	{
		return conditional_remarks;
	}
	public void setConditional_remarks(String conditional_remarks)
	{
		this.conditional_remarks = conditional_remarks;
	}

	
	
	public String getRemarks()
	{
		return remarks;
	}
	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}

	public String getType_of_issue() {
		return type_of_issue;
	}
	public void setType_of_issue(String type_of_issue) {
		this.type_of_issue = type_of_issue;
	}
	public String getLoan_auth() {
		return loan_auth;
	}
	public void setLoan_auth(String loan_auth) {
		this.loan_auth = loan_auth;
	}

	public int getLoan_duration() {
		return loan_duration;
	}
	public void setLoan_duration(int loan_duration) {
		this.loan_duration = loan_duration;
	}
	public String getQuantity_status() {
		return quantity_status;
	}
	public void setQuantity_status(String quantity_status) {
		this.quantity_status = quantity_status;
	}

	public String getBa_no() {
		return ba_no;
	}
	public void setBa_no(String ba_no) {
		this.ba_no = ba_no;
	}
	public String getType_of_vehicle() {
		return type_of_vehicle;
	}
	public void setType_of_vehicle(String type_of_vehicle) {
		this.type_of_vehicle = type_of_vehicle;
	}
	public String getCorps_sus_no() {
		return corps_sus_no;
	}
	public void setCorps_sus_no(String corps_sus_no) {
		this.corps_sus_no = corps_sus_no;
	}
	
 
}