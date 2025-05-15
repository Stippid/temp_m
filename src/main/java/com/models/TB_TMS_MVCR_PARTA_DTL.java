package com.models;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigInteger;
import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "tb_tms_mvcr_parta_dtl" , uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class TB_TMS_MVCR_PARTA_DTL {
	
	private int id;
	private String sus_no;
	private Date date_of_mvcr;
	private String issue_type;
	private String ba_no;
	private String classification;
	private String epmnt_clasfctn;
	private int kms_run;
	private Date cas_date;
	private String audit_check;
	private String source_unit_sus_no;
	private String destntn_sus_no;
	private String authrty_letter_no;
	private String cas_details;
	private String remarks;
	private String approved_by;
	private Date approve_date;
	private String creation_by;
	private Date creation_date;
	private String modify_by;
	private Date modify_date;
	private String deleted_by;
	private Date deleted_date;
	private int version_no;
	private String softdelete;
	private String filler_1;
	private String filler_2;
	private String filler_3;
	private String engine_no;
	private String app_rej_remarks;
	private String status;
	private String type_tpt;
	private BigInteger inlieu_mct;
	private String authority;
	private String accounting;
	private String rej_remarks;
	private String rej_status;
	private String qfd;
	private String miso_rmk;
	private String rio_no;
	private String ser_status;
	private String ser_reason;
	private String other_issue_type;
	
	private int mvcr_parta_id;
	private String oh_i_due;
	private String oh_i_done;
	private String oh_ii_due;
	private String oh_ii_done;
	private String oh_iii_due;
	private String oh_iii_done;
	private String mr_i_due;
	private String mr_i_done;
	private String mr_ii_due;
	private String mr_ii_done;
	private String mr_iii_due;
	private String mr_iii_done;
	private String mtd_launcher_type;
	private String launcher_regn_no;
	private String type_of_msls_fired;
	private int qty_of_msls_fired;
	private Date launcher_due;
	
	private Date launcher_done;
	private int qty_of_rds_fired;
	private int qty_of_rds_misfired;
	private String type_of_br_sys;
	private String br_sys_ser;
	private int no_of_passes;
	private int total_passes;
	private String type_of_rdr;
	private String rdr_ser;
	 private int version;
		
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
  	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "sus_no")
	public String getSus_no()
	{
		return sus_no;
	}
	public void setSus_no(String sus_no)
	{
		this.sus_no = sus_no;
	}

	@Column(name = "date_of_mvcr")
	public Date getDate_of_mvcr()
	{
		return date_of_mvcr;
	}
	public void setDate_of_mvcr(Date date_of_mvcr)
	{
		this.date_of_mvcr = date_of_mvcr;
	}

	@Column(name = "issue_type")
	public String getIssue_type()
	{
		return issue_type;
	}
	public void setIssue_type(String issue_type)
	{
		this.issue_type = issue_type;
	}

	@Column(name = "ba_no")
	public String getBa_no()
	{
		return ba_no;
	}
	public void setBa_no(String ba_no)
	{
		this.ba_no = ba_no;
	}

	@Column(name = "classification")
	public String getClassification()
	{
		return classification;
	}
	public void setClassification(String classification)
	{
		this.classification = classification;
	}

	@Column(name = "epmnt_clasfctn")
	public String getEpmnt_clasfctn()
	{
		return epmnt_clasfctn;
	}
	public void setEpmnt_clasfctn(String epmnt_clasfctn)
	{
		this.epmnt_clasfctn = epmnt_clasfctn;
	}
	
	@Column(name = "kms_run")
	public int getKms_run()
	{
		return kms_run;
	}
	public void setKms_run(int kms_run)
	{
		this.kms_run = kms_run;
	}

	@Column(name = "cas_date")
	public Date getCas_date()
	{
		return cas_date;
	}
	public void setCas_date(Date cas_date)
	{
		this.cas_date = cas_date;
	}
	
	@Column(name = "audit_check")
	public String getAudit_check()
	{
		return audit_check;
	}
	public void setAudit_check(String audit_check)
	{
		this.audit_check = audit_check;
	}

	
	@Column(name = "source_unit_sus_no")
	public String getSource_unit_sus_no()
	{
		return source_unit_sus_no;
	}
	public void setSource_unit_sus_no(String source_unit_sus_no)
	{
		this.source_unit_sus_no = source_unit_sus_no;
	}

	
	@Column(name = "destntn_sus_no")
	public String getDestntn_sus_no()
	{
		return destntn_sus_no;
	}
	public void setDestntn_sus_no(String destntn_sus_no)
	{
		this.destntn_sus_no = destntn_sus_no;
	}
	
	@Column(name = "authrty_letter_no")
	public String getAuthrty_letter_no()
	{
		return authrty_letter_no;
	}
	public void setAuthrty_letter_no(String authrty_letter_no)
	{
		this.authrty_letter_no = authrty_letter_no;
	}
	
	@Column(name = "cas_details")
	public String getCas_details()
	{
		return cas_details;
	}
	public void setCas_details(String cas_details)
	{
		this.cas_details = cas_details;
	}
	
	@Column(name = "remarks")
	public String getRemarks()
	{
		return remarks;
	}
	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}

	@Column(name = "approved_by")
	public String getApproved_by()
	{
		return approved_by;
	}
	public void setApproved_by(String approved_by)
	{
		this.approved_by = approved_by;
	}

	@Column(name = "approve_date")
	public Date getApprove_date()
	{
		return approve_date;
	}
	public void setApprove_date(Date approve_date)
	{
		this.approve_date = approve_date;
	}

	@Column(name = "creation_by")
	public String getCreation_by()
	{
		return creation_by;
	}
	public void setCreation_by(String creation_by)
	{
		this.creation_by = creation_by;
	}

	@Column(name = "creation_date")
	public Date getCreation_date()
	{
		return creation_date;
	}
	public void setCreation_date(Date creation_date)
	{
		this.creation_date = creation_date;
	}

	@Column(name = "modify_by")
	public String getModify_by()
	{
		return modify_by;
	}
	public void setModify_by(String modify_by)
	{
		this.modify_by = modify_by;
	}

	@Column(name = "modify_date")
	public Date getModify_date()
	{
		return modify_date;
	}
	public void setModify_date(Date modify_date)
	{
		this.modify_date = modify_date;
	}

	@Column(name = "deleted_by")
	public String getDeleted_by()
	{
		return deleted_by;
	}
	public void setDeleted_by(String deleted_by)
	{
		this.deleted_by = deleted_by;
	}

	@Column(name = "deleted_date")
	public Date getDeleted_date()
	{
		return deleted_date;
	}
	public void setDeleted_date(Date deleted_date)
	{
		this.deleted_date = deleted_date;
	}

	@Column(name = "version_no")
	public int getVersion_no()
	{
		return version_no;
	}
	public void setVersion_no(int version_no)
	{
		this.version_no = version_no;
	}

	@Column(name = "softdelete")
	public String getSoftdelete()
	{
		return softdelete;
	}
	public void setSoftdelete(String softdelete)
	{
		this.softdelete = softdelete;
	}

	@Column(name = "filler_1")
	public String getFiller_1()
	{
		return filler_1;
	}
	public void setFiller_1(String filler_1)
	{
		this.filler_1 = filler_1;
	}

	@Column(name = "filler_2")
	public String getFiller_2()
	{
		return filler_2;
	}
	public void setFiller_2(String filler_2)
	{
		this.filler_2 = filler_2;
	}

	@Column(name = "filler_3")
	public String getFiller_3()
	{
		return filler_3;
	}
	public void setFiller_3(String filler_3)
	{
		this.filler_3 = filler_3;
	}

	@Column(name = "engine_no")
	public String getEngine_no()
	{
		return engine_no;
	}
	public void setEngine_no(String engine_no)
	{
		this.engine_no = engine_no;
	}

	@Column(name = "app_rej_remarks")
	public String getApp_rej_remarks()
	{
		return app_rej_remarks;
	}
	public void setApp_rej_remarks(String app_rej_remarks)
	{
		this.app_rej_remarks = app_rej_remarks;
	}

	@Column(name = "status")
	public String getStatus()
	{
		return status;
	}
	public void setStatus(String status)
	{
		this.status = status;
	}

	@Column(name = "type_tpt")
	public String getType_tpt()
	{
		return type_tpt;
	}
	public void setType_tpt(String type_tpt)
	{
		this.type_tpt = type_tpt;
	}

	@Column(name = "inlieu_mct")
	public BigInteger getInlieu_mct() {
		return inlieu_mct;
	}
	public void setInlieu_mct(BigInteger inlieu_mct) {
		this.inlieu_mct = inlieu_mct;
	}

	@Column(name = "authority")
	public String getAuthority()
	{
		return authority;
	}
	public void setAuthority(String authority)
	{
		this.authority = authority;
	}

	@Column(name = "accounting")
	public String getAccounting()
	{
		return accounting;
	}
	public void setAccounting(String accounting)
	{
		this.accounting = accounting;
	}

	@Column(name = "rej_remarks")
	public String getRej_remarks()
	{
		return rej_remarks;
	}
	public void setRej_remarks(String rej_remarks)
	{
		this.rej_remarks = rej_remarks;
	}

	@Column(name = "rej_status")
	public String getRej_status()
	{
		return rej_status;
	}
	public void setRej_status(String rej_status)
	{
		this.rej_status = rej_status;
	}

	@Column(name = "qfd")
	public String getQfd()
	{
		return qfd;
	}
	public void setQfd(String qfd)
	{
		this.qfd = qfd;
	}
	
	public String getMiso_rmk() {
		return miso_rmk;
	}
	public void setMiso_rmk(String miso_rmk) {
		this.miso_rmk = miso_rmk;
	}
	
	public String getRio_no() {
		return rio_no;
	}
	public void setRio_no(String rio_no) {
		this.rio_no = rio_no;
	}
	public String getSer_status() {
		return ser_status;
	}
	public void setSer_status(String ser_status) {
		this.ser_status = ser_status;
	}
	public String getSer_reason() {
		return ser_reason;
	}
	public void setSer_reason(String ser_reason) {
		this.ser_reason = ser_reason;
	}
	public String getOther_issue_type() {
		return other_issue_type;
	}
	public void setOther_issue_type(String other_issue_type) {
		this.other_issue_type = other_issue_type;
	}
	public int getMvcr_parta_id() {
		return mvcr_parta_id;
	}
	public void setMvcr_parta_id(int mvcr_parta_id) {
		this.mvcr_parta_id = mvcr_parta_id;
	}
	public String getOh_i_due() {
		return oh_i_due;
	}
	public void setOh_i_due(String oh_i_due) {
		this.oh_i_due = oh_i_due;
	}
	public String getOh_i_done() {
		return oh_i_done;
	}
	public void setOh_i_done(String oh_i_done) {
		this.oh_i_done = oh_i_done;
	}
	public String getOh_ii_due() {
		return oh_ii_due;
	}
	public void setOh_ii_due(String oh_ii_due) {
		this.oh_ii_due = oh_ii_due;
	}
	public String getOh_ii_done() {
		return oh_ii_done;
	}
	public void setOh_ii_done(String oh_ii_done) {
		this.oh_ii_done = oh_ii_done;
	}
	public String getOh_iii_due() {
		return oh_iii_due;
	}
	public void setOh_iii_due(String oh_iii_due) {
		this.oh_iii_due = oh_iii_due;
	}
	public String getOh_iii_done() {
		return oh_iii_done;
	}
	public void setOh_iii_done(String oh_iii_done) {
		this.oh_iii_done = oh_iii_done;
	}
	public String getMr_i_due() {
		return mr_i_due;
	}
	public void setMr_i_due(String mr_i_due) {
		this.mr_i_due = mr_i_due;
	}
	public String getMr_i_done() {
		return mr_i_done;
	}
	public void setMr_i_done(String mr_i_done) {
		this.mr_i_done = mr_i_done;
	}
	public String getMr_ii_due() {
		return mr_ii_due;
	}
	public void setMr_ii_due(String mr_ii_due) {
		this.mr_ii_due = mr_ii_due;
	}
	public String getMr_ii_done() {
		return mr_ii_done;
	}
	public void setMr_ii_done(String mr_ii_done) {
		this.mr_ii_done = mr_ii_done;
	}
	public String getMr_iii_due() {
		return mr_iii_due;
	}
	public void setMr_iii_due(String mr_iii_due) {
		this.mr_iii_due = mr_iii_due;
	}
	public String getMr_iii_done() {
		return mr_iii_done;
	}
	public void setMr_iii_done(String mr_iii_done) {
		this.mr_iii_done = mr_iii_done;
	}
	public String getMtd_launcher_type() {
		return mtd_launcher_type;
	}
	public void setMtd_launcher_type(String mtd_launcher_type) {
		this.mtd_launcher_type = mtd_launcher_type;
	}
	public String getLauncher_regn_no() {
		return launcher_regn_no;
	}
	public void setLauncher_regn_no(String launcher_regn_no) {
		this.launcher_regn_no = launcher_regn_no;
	}
	public String getType_of_msls_fired() {
		return type_of_msls_fired;
	}
	public void setType_of_msls_fired(String type_of_msls_fired) {
		this.type_of_msls_fired = type_of_msls_fired;
	}
	public int getQty_of_msls_fired() {
		return qty_of_msls_fired;
	}
	public void setQty_of_msls_fired(int qty_of_msls_fired) {
		this.qty_of_msls_fired = qty_of_msls_fired;
	}
	public Date getLauncher_due() {
		return launcher_due;
	}
	public void setLauncher_due(Date launcher_due) {
		this.launcher_due = launcher_due;
	}
	public Date getLauncher_done() {
		return launcher_done;
	}
	public void setLauncher_done(Date launcher_done) {
		this.launcher_done = launcher_done;
	}
	public int getQty_of_rds_fired() {
		return qty_of_rds_fired;
	}
	public void setQty_of_rds_fired(int qty_of_rds_fired) {
		this.qty_of_rds_fired = qty_of_rds_fired;
	}
	public int getQty_of_rds_misfired() {
		return qty_of_rds_misfired;
	}
	public void setQty_of_rds_misfired(int qty_of_rds_misfired) {
		this.qty_of_rds_misfired = qty_of_rds_misfired;
	}
	public String getType_of_br_sys() {
		return type_of_br_sys;
	}
	public void setType_of_br_sys(String type_of_br_sys) {
		this.type_of_br_sys = type_of_br_sys;
	}
	public String getBr_sys_ser() {
		return br_sys_ser;
	}
	public void setBr_sys_ser(String br_sys_ser) {
		this.br_sys_ser = br_sys_ser;
	}
	public int getNo_of_passes() {
		return no_of_passes;
	}
	public void setNo_of_passes(int no_of_passes) {
		this.no_of_passes = no_of_passes;
	}
	public int getTotal_passes() {
		return total_passes;
	}
	public void setTotal_passes(int total_passes) {
		this.total_passes = total_passes;
	}
	public String getType_of_rdr() {
		return type_of_rdr;
	}
	public void setType_of_rdr(String type_of_rdr) {
		this.type_of_rdr = type_of_rdr;
	}
	public String getRdr_ser() {
		return rdr_ser;
	}
	public void setRdr_ser(String rdr_ser) {
		this.rdr_ser = rdr_ser;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}

}