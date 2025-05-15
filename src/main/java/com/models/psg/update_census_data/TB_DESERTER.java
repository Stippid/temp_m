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

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tb_psg_deserter", uniqueConstraints = { @UniqueConstraint(columnNames = "id"), })
public class TB_DESERTER {

	
	 	private int id;
	 	private String deserter;
	 	private String arm_type;
	 	private String arm_type_weapon;
	 	@DateTimeFormat(pattern = "yyyy-MM-dd")
	    private Date dt_desertion; 
	 	@DateTimeFormat(pattern = "yyyy-MM-dd")
	    private Date dt_recovered;
	 	private String desertion_cause;
	 	private String indl_class;
	 	private String ot_desertion_cause;
	 	private String recovered_arms;
		private int census_id;
		private BigInteger comm_id;
	    private String created_by;
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    private Date created_date; 
	    private String approved_by; 
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
		private Date approved_date;
	    private int status;
	    private int cancel_status=-1;
		private String cancel_by;
		private Date cancel_date;
		private String reject_remarks;
		
		
		
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDeserter() {
		return deserter;
	}
	public void setDeserter(String deserter) {
		this.deserter = deserter;
	}
	public String getArm_type() {
		return arm_type;
	}
	public void setArm_type(String arm_type) {
		this.arm_type = arm_type;
	}
	public String getArm_type_weapon() {
		return arm_type_weapon;
	}
	public void setArm_type_weapon(String arm_type_weapon) {
		this.arm_type_weapon = arm_type_weapon;
	}
	public Date getDt_desertion() {
		return dt_desertion;
	}
	public void setDt_desertion(Date dt_desertion) {
		this.dt_desertion = dt_desertion;
	}
	public Date getDt_recovered() {
		return dt_recovered;
	}
	public void setDt_recovered(Date dt_recovered) {
		this.dt_recovered = dt_recovered;
	}
	public String getDesertion_cause() {
		return desertion_cause;
	}
	public void setDesertion_cause(String desertion_cause) {
		this.desertion_cause = desertion_cause;
	}
	public String getIndl_class() {
		return indl_class;
	}
	public void setIndl_class(String indl_class) {
		this.indl_class = indl_class;
	}
	public String getOt_desertion_cause() {
		return ot_desertion_cause;
	}
	public void setOt_desertion_cause(String ot_desertion_cause) {
		this.ot_desertion_cause = ot_desertion_cause;
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
	public int getCensus_id() {
		return census_id;
	}
	public void setCensus_id(int census_id) {
		this.census_id = census_id;
	}
	
	public BigInteger getComm_id() {
		return comm_id;
	}
	public void setComm_id(BigInteger comm_id) {
		this.comm_id = comm_id;
	}
	public String getApproved_by() {
		return approved_by;
	}
	public void setApproved_by(String approved_by) {
		this.approved_by = approved_by;
	}
    public Date getApproved_date() {
		return approved_date;
	}
	public void setApproved_date(Date approved_date) {
		this.approved_date = approved_date;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getRecovered_arms() {
		return recovered_arms;
	}
	public void setRecovered_arms(String recovered_arms) {
		this.recovered_arms = recovered_arms;
	}
	public String getReject_remarks() {
		return reject_remarks;
	}
	public void setReject_remarks(String reject_remarks) {
		this.reject_remarks = reject_remarks;
	}
	
	
	
	
	
}
