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
@Table(name = "tb_tms_emar_history_table", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class TB_TMS_EMAR_HISTORY_TABLE {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id; 
	private int  er_id;
	private String sus_no;
	private String em_no;
	private String proc_from;
	private String classification;
	private String serviceable; 
	private String reasons; 
	private BigInteger current_km_run; 
	private BigInteger prev_km_run; 
	private BigInteger total_km_run;
	private String status; 
	private String approved_by;
	private Date approve_date;
	private String craetion_by;
	private Date creation_date;
	private String modify_by; 
	private Date modify_date; 
	private String deleted_by; 
	private Date deleted_date; 
	private String creation_by; 
//	private String seviceable; 
	private String balh_for; 
	private String pers; 
	private String rispares;
	private int data_updated;
	
	//added by MItesh
	private String oh_state;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date_of_oh;
	private Date save_date;
	private String hydraullic_sys_type;
	private String hydraullic_sys_ser_status;
	private String work_att_ser;
	private String type_of_engine;
	private String main_clutch_ser;
	private String under_carriage_assy_ser;
	private String final_drive_ser;
	private String electrical_sys_ser;
	private String steer_brake_sys_ser;
	private String Spare_demand;


	@Column(name = "engine_failure_at", columnDefinition = "INT DEFAULT 0")
    private int engine_failure_at = 0;
    
    @Column(name = "version", columnDefinition = "INT DEFAULT 0")
    private int version = 0;


	public String getSpare_demand() {
		return Spare_demand;
	}
	public void setSpare_demand(String spare_demand) {
		Spare_demand = spare_demand;
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
	public Date getSave_date() {
		return save_date;
	}
	public void setSave_date(Date save_date) {
		this.save_date = save_date;
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
	public int getEngine_failure_at() {
		return engine_failure_at;
	}
	public void setEngine_failure_at(int engine_failure_at) {
		this.engine_failure_at = engine_failure_at;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEr_id() {
		return er_id;
	}
	public void setEr_id(int er_id) {
		this.er_id = er_id;
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
	public String getServiceable() {
		return serviceable;
	}
	public void setServiceable(String serviceable) {
		this.serviceable = serviceable;
	}
	public String getReasons() {
		return reasons;
	}
	public void setReasons(String reasons) {
		this.reasons = reasons;
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
	public String getCraetion_by() {
		return craetion_by;
	}
	public void setCraetion_by(String craetion_by) {
		this.craetion_by = craetion_by;
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
	public String getCreation_by() {
		return creation_by;
	}
	public void setCreation_by(String creation_by) {
		this.creation_by = creation_by;
	}
//	public String getSeviceable() {
//		return seviceable;
//	}
//	public void setSeviceable(String seviceable) {
//		this.seviceable = seviceable;
//	}
	public String getBalh_for() {
		return balh_for;
	}
	public void setBalh_for(String balh_for) {
		this.balh_for = balh_for;
	}
	public String getPers() {
		return pers;
	}
	public void setPers(String pers) {
		this.pers = pers;
	}
	public String getRispares() {
		return rispares;
	}
	public void setRispares(String rispares) {
		this.rispares = rispares;
	}
	public int getData_updated() {
		return data_updated;
	}
	public void setData_updated(int data_updated) {
		this.data_updated = data_updated;
	}
	
	
	public TB_TMS_EMAR_HISTORY_TABLE() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TB_TMS_EMAR_HISTORY_TABLE(int id, int er_id, String sus_no, String em_no, String proc_from,
			String classification, String serviceable, String reasons, BigInteger current_km_run,
			BigInteger prev_km_run, BigInteger total_km_run, String status, String approved_by, Date approve_date,
			String craetion_by, Date creation_date, String modify_by, Date modify_date, String deleted_by,
			Date deleted_date, String creation_by, String balh_for, String pers, String rispares) {
		super();
		this.id = id;
		this.er_id = er_id;
		this.sus_no = sus_no;
		this.em_no = em_no;
		this.proc_from = proc_from;
		this.classification = classification;
		this.serviceable = serviceable;
		this.reasons = reasons;
		this.current_km_run = current_km_run;
		this.prev_km_run = prev_km_run;
		this.total_km_run = total_km_run;
		this.status = status;
		this.approved_by = approved_by;
		this.approve_date = approve_date;
		this.craetion_by = craetion_by;
		this.creation_date = creation_date;
		this.modify_by = modify_by;
		this.modify_date = modify_date;
		this.deleted_by = deleted_by;
		this.deleted_date = deleted_date;
		this.creation_by = creation_by;
//		this.seviceable = seviceable;
		this.balh_for = balh_for;
		this.pers = pers;
		this.rispares = rispares;
	}
	

}
