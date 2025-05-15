package com.models;
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
@Table(name = "tb_tms_emar_report", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class TB_TMS_EMAR_REPORT {
	
	private int id;
	private String sus_no;
	private String em_no;
	private String proc_from;
	private String classification;
	private String seviceable;
	private String ser_reason ="0";
	private String reasons;
	private String spare_demand;
	private BigInteger current_km_run;
	private BigInteger prev_km_run;
	private BigInteger total_km_run;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date_of_oh;
	
	private String Balh_for;
	private String status;
	private String aprv_rejec_remarks;
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
	
	private String rispares;
	private String pers;
	private String oh_state;
	
	private int engine_failure_at;
	private String hydraullic_sys_type;
	private String hydraullic_sys_ser_status;
	private String work_att_ser;
	private String type_of_engine;
	private String main_clutch_ser;
	private String under_carriage_assy_ser;
	private String final_drive_ser;
	private String electrical_sys_ser;
	private String steer_brake_sys_ser;
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
	public String getSus_no() {
		return sus_no;
	}
	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}
	public String getEm_no() {
		return em_no;
	}
	public void setEm_no(String em_no) {
		this.em_no = em_no;
	}
	public String getProc_from() {
		return proc_from;
	}
	public void setProc_from(String proc_from) {
		this.proc_from = proc_from;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public String getSeviceable() {
		return seviceable;
	}
	public void setSeviceable(String seviceable) {
		this.seviceable = seviceable;
	}
	public String getReasons() {
		return reasons;
	}
	public void setReasons(String reasons) {
		this.reasons = reasons;
	}
	public String getSpare_demand() {
		return spare_demand;
	}
	public void setSpare_demand(String spare_demand) {
		this.spare_demand = spare_demand;
	}
	
	public BigInteger getCurrent_km_run() {
		return current_km_run;
	}
	public void setCurrent_km_run(BigInteger current_km_run) {
		this.current_km_run = current_km_run;
	}
	public BigInteger getPrev_km_run() {
		return prev_km_run;
	}
	public void setPrev_km_run(BigInteger prev_km_run) {
		this.prev_km_run = prev_km_run;
	}
	public BigInteger getTotal_km_run() {
		return total_km_run;
	}
	public void setTotal_km_run(BigInteger total_km_run) {
		this.total_km_run = total_km_run;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAprv_rejec_remarks() {
		return aprv_rejec_remarks;
	}
	public void setAprv_rejec_remarks(String aprv_rejec_remarks) {
		this.aprv_rejec_remarks = aprv_rejec_remarks;
	}
	public String getApproved_by() {
		return approved_by;
	}
	public void setApproved_by(String approved_by) {
		this.approved_by = approved_by;
	}
	public Date getApprove_date() {
		return approve_date;
	}
	public void setApprove_date(Date approve_date) {
		this.approve_date = approve_date;
	}
	public String getCreation_by() {
		return creation_by;
	}
	public void setCreation_by(String creation_by) {
		this.creation_by = creation_by;
	}
	public Date getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}
	public String getModify_by() {
		return modify_by;
	}
	public void setModify_by(String modify_by) {
		this.modify_by = modify_by;
	}
	public Date getModify_date() {
		return modify_date;
	}
	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}
	public String getDeleted_by() {
		return deleted_by;
	}
	public void setDeleted_by(String deleted_by) {
		this.deleted_by = deleted_by;
	}
	public Date getDeleted_date() {
		return deleted_date;
	}
	public void setDeleted_date(Date deleted_date) {
		this.deleted_date = deleted_date;
	}
	public int getVersion_no() {
		return version_no;
	}
	public void setVersion_no(int version_no) {
		this.version_no = version_no;
	}
	public String getSoftdelete() {
		return softdelete;
	}
	public void setSoftdelete(String softdelete) {
		this.softdelete = softdelete;
	}
	public String getFiller_1() {
		return filler_1;
	}
	public void setFiller_1(String filler_1) {
		this.filler_1 = filler_1;
	}
	public String getFiller_2() {
		return filler_2;
	}
	public void setFiller_2(String filler_2) {
		this.filler_2 = filler_2;
	}
	public String getFiller_3() {
		return filler_3;
	}
	public void setFiller_3(String filler_3) {
		this.filler_3 = filler_3;
	}
	public String getBalh_for() {
		return Balh_for;
	}
	public void setBalh_for(String balh_for) {
		Balh_for = balh_for;
	}
	
	public String getPers() {
		return pers;
	}
	public void setPers(String pers) {
		this.pers = pers;
	}
	public String getOh_state() {
		return oh_state;
	}
	public void setOh_state(String oh_state) {
		this.oh_state = oh_state;
	}
	public Date getDate_of_oh() {
		return date_of_oh;
	}
	public void setDate_of_oh(Date date_of_oh) {
		this.date_of_oh = date_of_oh;
	}
	public String getRispares() {
		return rispares;
	}
	public void setRispares(String rispares) {
		this.rispares = rispares;
	}
	public int getEngine_failure_at() {
		return engine_failure_at;
	}
	public void setEngine_failure_at(int engine_failure_at) {
		this.engine_failure_at = engine_failure_at;
	}
	public String getHydraullic_sys_type() {
		return hydraullic_sys_type;
	}
	public void setHydraullic_sys_type(String hydraullic_sys_type) {
		this.hydraullic_sys_type = hydraullic_sys_type;
	}
	public String getHydraullic_sys_ser_status() {
		return hydraullic_sys_ser_status;
	}
	public void setHydraullic_sys_ser_status(String hydraullic_sys_ser_status) {
		this.hydraullic_sys_ser_status = hydraullic_sys_ser_status;
	}
	public String getWork_att_ser() {
		return work_att_ser;
	}
	public void setWork_att_ser(String work_att_ser) {
		this.work_att_ser = work_att_ser;
	}
	public String getType_of_engine() {
		return type_of_engine;
	}
	public void setType_of_engine(String type_of_engine) {
		this.type_of_engine = type_of_engine;
	}
	public String getMain_clutch_ser() {
		return main_clutch_ser;
	}
	public void setMain_clutch_ser(String main_clutch_ser) {
		this.main_clutch_ser = main_clutch_ser;
	}
	public String getUnder_carriage_assy_ser() {
		return under_carriage_assy_ser;
	}
	public void setUnder_carriage_assy_ser(String under_carriage_assy_ser) {
		this.under_carriage_assy_ser = under_carriage_assy_ser;
	}
	public String getFinal_drive_ser() {
		return final_drive_ser;
	}
	public void setFinal_drive_ser(String final_drive_ser) {
		this.final_drive_ser = final_drive_ser;
	}
	public String getElectrical_sys_ser() {
		return electrical_sys_ser;
	}
	public void setElectrical_sys_ser(String electrical_sys_ser) {
		this.electrical_sys_ser = electrical_sys_ser;
	}
	public String getSteer_brake_sys_ser() {
		return steer_brake_sys_ser;
	}
	public void setSteer_brake_sys_ser(String steer_brake_sys_ser) {
		this.steer_brake_sys_ser = steer_brake_sys_ser;
	}
	
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getSer_reason() {
		return ser_reason;
	}
	public void setSer_reason(String ser_reason) {
		this.ser_reason = ser_reason;
	}

}
