package com.models;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigInteger;
import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "tb_tms_mvcr_parta_dtl_history" , uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class TB_TMS_MVCR_PARTA_DTL_HISTORY {
	
	private int id;
	private int mvcr_parta_id;
	private String sus_no;	
	private String issue_type;
	private String ba_no;
	private String classification;
	private String epmnt_clasfctn;
	private int kms_run;
	private String authrty_letter_no;
	private String remarks;
	private String creation_by;
	private Date creation_date;
	private String app_rej_remarks;
	private int status;
	private String miso_rmk;	
	private String ser_status;
	private String ser_reason;
	
	private String approved_by;
	private Date approved_date;
	private int data_updated;
	
	
	//added by Mitesh
	private Date save_date;
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
	private Date launcher_due;
	
	private Date launcher_done;
	private String type_of_br_sys;
	private String br_sys_ser;
	private String type_of_rdr;
	private String rdr_ser;
	
	private int qty_of_msls_fired = 0;
    	private int qty_of_rds_fired = 0;
    	private int qty_of_rds_misfired = 0;
    	private int no_of_passes = 0;
    	private int total_passes = 0;
    	private int version = 0;


		
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

	
	@Column(name = "authrty_letter_no")
	public String getAuthrty_letter_no()
	{
		return authrty_letter_no;
	}
	public void setAuthrty_letter_no(String authrty_letter_no)
	{
		this.authrty_letter_no = authrty_letter_no;
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
	public int getStatus()
	{
		return status;
	}
	public void setStatus(int status)
	{
		this.status = status;
	}

	public String getMiso_rmk() {
		return miso_rmk;
	}
	public void setMiso_rmk(String miso_rmk) {
		this.miso_rmk = miso_rmk;
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
  
	@Column(name = "mvcr_parta_id")
	public int getMvcr_parta_id() {
		return mvcr_parta_id;
	}
	public void setMvcr_parta_id(int mvcr_parta_id) {
		this.mvcr_parta_id = mvcr_parta_id;
	}
	
	@Column(name = "approved_by")
	public String getApproved_by() {
		return approved_by;
	}
	public void setApproved_by(String approved_by) {
		this.approved_by = approved_by;
	}
	
	@Column(name = "approved_date")
	public Date getApproved_date() {
		return approved_date;
	}
	public void setApproved_date(Date approved_date) {
		this.approved_date = approved_date;
	}
	public int getData_updated() {
		return data_updated;
	}
	public void setData_updated(int data_updated) {
		this.data_updated = data_updated;
	}
	@Column(name = "qty_of_msls_fired", columnDefinition = "INT DEFAULT 0")
    public int getQty_of_msls_fired() {
        return qty_of_msls_fired;
    }
	public void setQty_of_msls_fired(int qty_of_msls_fired) {
		this.qty_of_msls_fired = qty_of_msls_fired;
	}

    @Column(name = "qty_of_rds_fired", columnDefinition = "INT DEFAULT 0")
    public int getQty_of_rds_fired() {
        return qty_of_rds_fired;
    }
    public void setQty_of_rds_fired(int qty_of_rds_fired) {
		this.qty_of_rds_fired = qty_of_rds_fired;
	}

    @Column(name = "qty_of_rds_misfired", columnDefinition = "INT DEFAULT 0")
    public int getQty_of_rds_misfired() {
        return qty_of_rds_misfired;
    }
    public void setQty_of_rds_misfired(int qty_of_rds_misfired) {
		this.qty_of_rds_misfired = qty_of_rds_misfired;
	}

    @Column(name = "no_of_passes", columnDefinition = "INT DEFAULT 0")
    public int getNo_of_passes() {
        return no_of_passes;
    }
    public void setNo_of_passes(int no_of_passes) {
		this.no_of_passes = no_of_passes;
	}

    @Column(name = "total_passes", columnDefinition = "INT DEFAULT 0")
    public int getTotal_passes() {
        return total_passes;
    }
    public void setTotal_passes(int total_passes) {
		this.total_passes = total_passes;
	}

    @Column(name = "version", columnDefinition = "INT DEFAULT 0")
    public int getVersion() {
        return version;
    }
    public void setVersion(int version) {
		this.version = version;
	}
	public Date getSave_date() {
		return save_date;
	}
	public void setSave_date(Date save_date) {
		this.save_date = save_date;
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
	
}