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

@Entity
@Table(name = "tb_psg_change_of_comission", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
public class TB_PSG_CHANGE_OF_COMISSION {
	
	private BigInteger id;

	private String authority;
	private Date date_of_authority;
	private String new_personal_no;
	private Date date_of_permanent_commission;
	
	private Date date_of_seniority;
	
	private int type_of_commission_granted;
	private int census_id ;
	private BigInteger comm_id ;
	private int status;
	private String reject_remarks;
	
	private Date created_date;
	private String created_by;
	private Date modified_date;
	private String modified_by;
	
	private int cancel_status=-1;
	private String cancel_by;
	private Date cancel_date;
	
	private Date eff_date_of_seniority;
	
	private String old_personal_no;
	private int previous_commission;
	
	public Date getEff_date_of_seniority() {
		return eff_date_of_seniority;
	}
	public void setEff_date_of_seniority(Date eff_date_of_seniority) {
		this.eff_date_of_seniority = eff_date_of_seniority;
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
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	
	public String getAuthority() {
		return authority;
	}
	
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public Date getDate_of_authority() {
		return date_of_authority;
	}
	public void setDate_of_authority(Date date_of_authority) {
		this.date_of_authority = date_of_authority;
	}
	public String getNew_personal_no() {
		return new_personal_no;
	}
	public void setNew_personal_no(String new_personal_no) {
		this.new_personal_no = new_personal_no;
	}
	public Date getDate_of_permanent_commission() {
		return date_of_permanent_commission;
	}
	public void setDate_of_permanent_commission(Date date_of_permanent_commission) {
		this.date_of_permanent_commission = date_of_permanent_commission;
	}
	public Date getDate_of_seniority() {
		return date_of_seniority;
	}
	public void setDate_of_seniority(Date date_of_seniority) {
		this.date_of_seniority = date_of_seniority;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}	
	public String getReject_remarks() {
		return reject_remarks;
	}
	public void setReject_remarks(String reject_remarks) {
		this.reject_remarks = reject_remarks;
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
	
	public int getType_of_commission_granted() {
		return type_of_commission_granted;
	}
	public void setType_of_commission_granted(int type_of_commission_granted) {
		this.type_of_commission_granted = type_of_commission_granted;
	}
	public int getCensus_id() {
		return census_id;
	}
	public void setCensus_id(int census_id) {
		this.census_id = census_id;
	}
	
	public String getOld_personal_no() {
		return old_personal_no;
	}
	public void setOld_personal_no(String old_personal_no) {
		this.old_personal_no = old_personal_no;
	}
	public int getPrevious_commission() {
		return previous_commission;
	}
	public void setPrevious_commission(int previous_commission) {
		this.previous_commission = previous_commission;
	}
	public BigInteger getComm_id() {
		return comm_id;
	}
	public void setComm_id(BigInteger comm_id) {
		this.comm_id = comm_id;
	}
	

	

}
