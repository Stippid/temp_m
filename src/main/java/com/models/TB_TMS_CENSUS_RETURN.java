package com.models;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "tb_tms_census_retrn" , uniqueConstraints = {@UniqueConstraint(columnNames = "id"),@UniqueConstraint(columnNames = "ba_no")})
public class TB_TMS_CENSUS_RETURN {
	
	  private int id;
	  private String sus_no;
	  private Date date_of_cens_retrn; 
	  private Date dt_of_submsn;
	  private String ba_no;
	  private String vehcl_classfctn;
	  private int vehcl_kms_run;
	  private int track_kms;
	  private String engine_type;
	  private String engine_kms_run;
	  private String engine_hrs_run;
	  private String aux_engine_run;
	  private String aux_engn_clasfctn;
	  private int aux_engn_hrs_run;
	  private String main_gun_type;
	  private String main_gun_efc;
	  private String main_gun_qr;
	  private String sec_gun_type;
	  private String main_radio_set_nomcltr;
	  private String main_radio_set;
	  private String main_radio_set_condn;
	  private String unit_remarks;
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
	  private String veh_km_run_period;
	  private String aux_type;
	  private String main_radio_set_uh;
	  private String miso_remarks;
	  private String mr1_due;
	  private String mr1_done;
	  private String oh1_due;
	  private String oh1_done;
	  private String oh1_detl;
	  private String mr2_due;
	  private String mr2_done;
	  private String oh2_due;
	  private String oh2_done;
	  private String oh2_detl;
	  private String mr3_due;
	  private String mr3_done;
	  private String oh3_due;
	  private String oh3_done;
	  private String issued_type;
	  
	  private String ser_status;
	  private String ser_reason;
	  
	  private String engine_no;
	  private int engine_hrs_current;
	  private int engine_hr_total;
	  private int engine_failure_at;
	  private Date engine_submission_dt;
	  private Date engine_repair_comp_dt;
	  private String air_compressor_no;
	  private String air_compressor_type;
	  private int air_compressor_total_hrs;
	  private int air_compressor_failure_at;
	  private Date air_compressor_submission_dt;
	  private Date air_compressor_repair_comp_dt;
	  private String sgb_no;
	  private String sgb_type;
	  private int sgb_total_km;
	  private Date sgb_submission_dt;
	  private Date sgb_repair_comp_dt;
	  private String igb_no;
	  
	  private String igb_type;
	  private int igb_total_km;
	  private Date igb_submission_dt;
	  private Date igb_repair_comp_dt;
	  private String track_assy_ser;
	  private int sprocket_assy_total_km;
	  private String sprocket_assy_ser;
	  private int pump_drive_motor_hr;
	  private String pump_drive_motor_ser;
	  private int starter_genr_total_hr;
	  private String starter_genr_ser;
	  private String turbo_charger_type;
	  private int turbo_charger_defect_at;
	  private Date turbo_charger_submission_dt;
	  private Date turbo_charger_repair_comp_dt;
	  private Date engine_demand_dt;
	  private Date engine_rel_dt;
	  private int fire_fight_sys_cyl_auth;
	  
	  private int fire_fight_sys_cyl_held;
	  private int fire_fight_sys_cyl_empty;
	  private Date fire_fight_cyl_demand_dt;
	  private String cbrn_sys_over_pressure;
	  private int cbrn_filter_qty;
	  private String cbrn_filter_ser;
	  private String pkuza_ser;
	  private String bas_a_ser;
	  private String cbrn_spl_blower_ser;
	  private String mine_plough_ser;
	  private String emp_ser;
	  private String mrs_ser;
	  private String dch_installed;
	  private String dch_type;
	  private Date dch_installed_dt;
	  private Date dch_vintage;
	  private String dch_ser;
	  private Date cti_install_dt;
	  
	  private Date cti_vintage;
	  private String cti_ser;
	  private String main_gun_article_no;
	  private String main_gun_article_ser;
	  private int no_of_msls_fired;
	  private int no_of_msls_failed;
	  private String sec_gun_regn_no;
	  private String sec_gun_ser;
	  private String aa_gun_type;
	  private String aa_gun_regn_no;
	  private String aa_gun_ser;
	  private String msl_launcher_regn_no;
	  private String msl_launcher_ser;
	  private String msl_launcher_oh_due;
	  private Date msl_lr_oh_done_dt;
	  private Date tisas_old_instln_dt;
	  private Date tisas_old_vintage;
	  private String tisas_old_ser;
	  
	  private Date tisas_new_instln_dt;
	  private Date tisas_new_vintage;
	  private String tisas_new_ser;
	  private String tifcs_type;
	  private String tifcs_regn_no;
	  private Date tifcs_instln_dt;
	  private Date tifcs_vintage;
	  private int relay_kr_hr;
	  private String relay_kr_ser;
	  private int lrf_hr;
	  private String lrf_ser;
	  private int gyro_direc_indi_hr;
	  private String gyro_direc_indi_ser;
	  private int dist_box_hr;
	  private String dist_box_ser;
	  private int dist_box_failure_at;
	  private String tim_fitted;
	  private Date tim_intls_dt;
	  
	  private Date tim_vintage;
	  private String tis_fitted;
	  private Date tis_instaln_dt;
	  private Date tis_vintage;
	  private String u_tim_fitted;
	  private Date u_tim_intsln_dt;
	  private Date u_tim_vintage;
	  private String m_tim_fitted;
	  private Date m_tim_instln_dt;
	  private Date m_tim_vintage;
	  private String m_tisk_fitted;
	  private Date m_tisk_instals_dt;
	  private Date m_tisk_vintage;
	  private String dns_fitted;
	  private Date dns_instln_dt;
	  private String dns_ser;
	  private String cti_fitted;
	  private Date cti_instln_dt;
	  
	  private String alns_fitted;
	  private String alns_ser;
	  private String gps_fitted;
	  private String gps_ser;
	  private String ecu_fitted;
	  private String ecu_ser;
	  private String spta_fitted;
	  private String spta_ser;
	  private int sgb_failure_at;
	  private int igb_failure_at;
	  private int track_assy_failure_at;
	  private int sprocket_assy_failure_at;
	  private int pump_drive_motor_failure_at;
	  private int starter_genr_failure_at;
	  private int relay_kr_failure_at;
	  private int lrf_failure_at;
	  private int gyro_direc_failure_at;
	  private String mrs_type;
	  
	  private int mrs_qty;
	  private String type_gun_article;
	  private String main_gun_article_qtr;
	  private int main_gun_article_efc;
	  private String powerpack_ser;
	  private Date powerpack_demand_dt;
	  private String stab_ser;
	  private Date stab_demand_dt;
	  private String ifdss_ser;
	  private Date ifdss_demand_dt;
	  private String gms_ser;
	  private String gms_demand_dtcps_mk_ser;
	  private Date cps_mk_demand_dt;
	  private String dvr_pnvd_ser;
	  private Date dvr_pnvd_demand_dt;
	  private String fcc_ser;
	  private Date fcc_demand_dt;
	  private int bogie_wheels_held_onrd;
	  
	  private int bogie_wheels_held_offrd;
	  private Date bogie_wheels_demand_dt;
	  private String top_roll_ser;
	  private Date top_roll_demand_dt;
	  private int track_pads_held_onrd;
	  private int track_pads_held_offrd;
	  private Date track_pads_demand_dt;
	  private String cdr_cont_stn_ser;
	  private Date cdr_cont_stn_demmand_dt;
	  private String gcdu_ser;
	  private Date gcdu_demand_dt;
	   private int version;
	   private String low_tempered_bal;
	  

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "sus_no" , nullable = false)
	public String getSus_no() {
		return sus_no;
	}
	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getDate_of_cens_retrn() {
		return date_of_cens_retrn;
	}
	public void setDate_of_cens_retrn(Date date_of_cens_retrn) {
		this.date_of_cens_retrn = date_of_cens_retrn;
	}
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getDt_of_submsn() {
		return dt_of_submsn;
	}
	public void setDt_of_submsn(Date dt_of_submsn) {
		this.dt_of_submsn = dt_of_submsn;
	}
	
	@Column(name = "ba_no" ,unique = true, nullable = false)
	public String getBa_no() {
		return ba_no;
	}
	public void setBa_no(String ba_no) {
		this.ba_no = ba_no;
	}
	
	@Column(name = "vehcl_classfctn")
	public String getVehcl_classfctn() {
		return vehcl_classfctn;
	}
	public void setVehcl_classfctn(String vehcl_classfctn) {
		this.vehcl_classfctn = vehcl_classfctn;
	}
	
	@Column(name = "vehcl_kms_run")
	public int getVehcl_kms_run() {
		return vehcl_kms_run;
	}
	public void setVehcl_kms_run(int vehcl_kms_run) {
		this.vehcl_kms_run = vehcl_kms_run;
	}
	
	@Column(name = "track_kms")
	public int getTrack_kms() {
		return track_kms;
	}
	public void setTrack_kms(int track_kms) {
		this.track_kms = track_kms;
	}
	
	@Column(name = "engine_type")
	public String getEngine_type() {
		return engine_type;
	}
	public void setEngine_type(String engine_type) {
		this.engine_type = engine_type;
	}
	
	@Column(name = "engine_kms_run")
	public String getEngine_kms_run() {
		return engine_kms_run;
	}
	public void setEngine_kms_run(String engine_kms_run) {
		this.engine_kms_run = engine_kms_run;
	}
	
	@Column(name = "engine_hrs_run")
	public String  getEngine_hrs_run() {
		return engine_hrs_run;
	}
	public void setEngine_hrs_run(String engine_hrs_run) {
		this.engine_hrs_run = engine_hrs_run;
	}
	
	@Column(name = "aux_engine_run")
	public String getAux_engine_run() {
		return aux_engine_run;
	}
	public void setAux_engine_run(String aux_engine_run) {
		this.aux_engine_run = aux_engine_run;
	}
	
	@Column(name = "aux_engn_clasfctn")
	public String getAux_engn_clasfctn() {
		return aux_engn_clasfctn;
	}
	public void setAux_engn_clasfctn(String aux_engn_clasfctn) {
		this.aux_engn_clasfctn = aux_engn_clasfctn;
	}
	
	@Column(name = "aux_engn_hrs_run")
	public int getAux_engn_hrs_run() {
		return aux_engn_hrs_run;
	}
	public void setAux_engn_hrs_run(int aux_engn_hrs_run) {
		this.aux_engn_hrs_run = aux_engn_hrs_run;
	}
	
	@Column(name = "main_gun_type")
	public String getMain_gun_type() {
		return main_gun_type;
	}
	public void setMain_gun_type(String main_gun_type) {
		this.main_gun_type = main_gun_type;
	}
	
	@Column(name = "main_gun_efc")
	public String getMain_gun_efc() {
		return main_gun_efc;
	}
	public void setMain_gun_efc(String main_gun_efc) {
		this.main_gun_efc = main_gun_efc;
	}
	
	@Column(name = "main_gun_qr")
	public String getMain_gun_qr() {
		return main_gun_qr;
	}
	public void setMain_gun_qr(String main_gun_qr) {
		this.main_gun_qr = main_gun_qr;
	}
	
	@Column(name = "sec_gun_type")
	public String getSec_gun_type() {
		return sec_gun_type;
	}
	public void setSec_gun_type(String sec_gun_type) {
		this.sec_gun_type = sec_gun_type;
	}
	
	@Column(name = "main_radio_set_nomcltr")
	public String getMain_radio_set_nomcltr() {
		return main_radio_set_nomcltr;
	}
	public void setMain_radio_set_nomcltr(String main_radio_set_nomcltr) {
		this.main_radio_set_nomcltr = main_radio_set_nomcltr;
	}
	
	@Column(name = "main_radio_set")
	public String getMain_radio_set() {
		return main_radio_set;
	}
	public void setMain_radio_set(String main_radio_set) {
		this.main_radio_set = main_radio_set;
	}
	
	@Column(name = "main_radio_set_condn")
	public String getMain_radio_set_condn() {
		return main_radio_set_condn;
	}
	public void setMain_radio_set_condn(String main_radio_set_condn) {
		this.main_radio_set_condn = main_radio_set_condn;
	}
	
	@Column(name = "unit_remarks")
	public String getUnit_remarks() {
		return unit_remarks;
	}
	public void setUnit_remarks(String unit_remarks) {
		this.unit_remarks = unit_remarks;
	}
	
	@Column(name = "status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "aprv_rejec_remarks")
	public String getAprv_rejec_remarks() {
		return aprv_rejec_remarks;
	}
	public void setAprv_rejec_remarks(String aprv_rejec_remarks) {
		this.aprv_rejec_remarks = aprv_rejec_remarks;
	}
	
	@Column(name = "approved_by")
	public String getApproved_by() {
		return approved_by;
	}
	public void setApproved_by(String approved_by) {
		this.approved_by = approved_by;
	}
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getApprove_date() {
		return approve_date;
	}
	public void setApprove_date(Date approve_date) {
		this.approve_date = approve_date;
	}
	
	@Column(name = "creation_by")
	public String getCreation_by() {
		return creation_by;
	}
	public void setCreation_by(String creation_by) {
		this.creation_by = creation_by;
	}
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}
	
	@Column(name = "modify_by")
	public String getModify_by() {
		return modify_by;
	}
	public void setModify_by(String modify_by) {
		this.modify_by = modify_by;
	}
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getModify_date() {
		return modify_date;
	}
	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}
	
	@Column(name = "deleted_by")
	public String getDeleted_by() {
		return deleted_by;
	}
	public void setDeleted_by(String deleted_by) {
		this.deleted_by = deleted_by;
	}
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getDeleted_date() {
		return deleted_date;
	}
	public void setDeleted_date(Date deleted_date) {
		this.deleted_date = deleted_date;
	}
	
	@Column(name = "version_no")
	public int getVersion_no() {
		return version_no;
	}
	public void setVersion_no(int version_no) {
		this.version_no = version_no;
	}
	
	@Column(name = "softdelete")
	public String getSoftdelete() {
		return softdelete;
	}
	public void setSoftdelete(String softdelete) {
		this.softdelete = softdelete;
	}
	
	
	@Column(name = "filler_1")
	public String getFiller_1() {
		return filler_1;
	}
	public void setFiller_1(String filler_1) {
		this.filler_1 = filler_1;
	}
	
	@Column(name = "filler_2")
	public String getFiller_2() {
		return filler_2;
	}
	public void setFiller_2(String filler_2) {
		this.filler_2 = filler_2;
	}
	
	@Column(name = "filler_3")
	public String getFiller_3() {
		return filler_3;
	}
	public void setFiller_3(String filler_3) {
		this.filler_3 = filler_3;
	}
	
	@Column(name = "veh_km_run_period")
	public String getVeh_km_run_period() {
		return veh_km_run_period;
	}
	public void setVeh_km_run_period(String veh_km_run_period) {
		this.veh_km_run_period = veh_km_run_period;
	}

	@Column(name = "aux_type")
	public String getAux_type() {
		return aux_type;
	}
	public void setAux_type(String aux_type) {
		this.aux_type = aux_type;
	}
	
	@Column(name = "main_radio_set_uh")
	public String getMain_radio_set_uh() {
		return main_radio_set_uh;
	}
	public void setMain_radio_set_uh(String main_radio_set_uh) {
		this.main_radio_set_uh = main_radio_set_uh;
	}
	
	@Column(name = "miso_remarks")
	public String getMiso_remarks() {
		return miso_remarks;
	}
	public void setMiso_remarks(String miso_remarks) {
		this.miso_remarks = miso_remarks;
	}
	
	@Column(name = "mr1_due")
	public String getMr1_due() {
		return mr1_due;
	}
	public void setMr1_due(String mr1_due) {
		this.mr1_due = mr1_due;
	}
	
	@Column(name = "mr1_done")
	public String getMr1_done() {
		return mr1_done;
	}
	public void setMr1_done(String mr1_done) {
		this.mr1_done = mr1_done;
	}
	
	@Column(name = "oh1_due")
	public String getOh1_due() {
		return oh1_due;
	}
	public void setOh1_due(String oh1_due) {
		this.oh1_due = oh1_due;
	}
	
	@Column(name = "oh1_done")
	public String getOh1_done() {
		return oh1_done;
	}
	public void setOh1_done(String oh1_done) {
		this.oh1_done = oh1_done;
	}
	
	@Column(name = "oh1_detl")
	public String getOh1_detl() {
		return oh1_detl;
	}
	public void setOh1_detl(String oh1_detl) {
		this.oh1_detl = oh1_detl;
	}
	
	@Column(name = "mr2_due")
	public String getMr2_due() {
		return mr2_due;
	}
	public void setMr2_due(String mr2_due) {
		this.mr2_due = mr2_due;
	}
	
	
	@Column(name = "mr2_done")
	public String getMr2_done() {
		return mr2_done;
	}
	public void setMr2_done(String mr2_done) {
		this.mr2_done = mr2_done;
	}
	
	@Column(name = "oh2_due")
	public String getOh2_due() {
		return oh2_due;
	}
	public void setOh2_due(String oh2_due) {
		this.oh2_due = oh2_due;
	}
	
	@Column(name = "oh2_done")
	public String getOh2_done() {
		return oh2_done;
	}
	public void setOh2_done(String oh2_done) {
		this.oh2_done = oh2_done;
	}
	
	@Column(name = "oh2_detl")
	public String getOh2_detl() {
		return oh2_detl;
	}
	public void setOh2_detl(String oh2_detl) {
		this.oh2_detl = oh2_detl;
	}
	
	@Column(name = "mr3_due")
	public String getMr3_due() {
		return mr3_due;
	}
	public void setMr3_due(String mr3_due) {
		this.mr3_due = mr3_due;
	}
	
	@Column(name = "mr3_done")
	public String getMr3_done() {
		return mr3_done;
	}
	public void setMr3_done(String mr3_done) {
		this.mr3_done = mr3_done;
	}
	public String getIssued_type() {
		return issued_type;
	}
	public void setIssued_type(String issued_type) {
		this.issued_type = issued_type;
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
	public String getOh3_due() {
		return oh3_due;
	}
	public void setOh3_due(String oh3_due) {
		this.oh3_due = oh3_due;
	}
	public String getOh3_done() {
		return oh3_done;
	}
	public void setOh3_done(String oh3_done) {
		this.oh3_done = oh3_done;
	}
	public String getEngine_no() {
		return engine_no;
	}
	public void setEngine_no(String engine_no) {
		this.engine_no = engine_no;
	}
	public int getEngine_hrs_current() {
		return engine_hrs_current;
	}
	public void setEngine_hrs_current(int engine_hrs_current) {
		this.engine_hrs_current = engine_hrs_current;
	}
	public int getEngine_hr_total() {
		return engine_hr_total;
	}
	public void setEngine_hr_total(int engine_hr_total) {
		this.engine_hr_total = engine_hr_total;
	}
	public int getEngine_failure_at() {
		return engine_failure_at;
	}
	public void setEngine_failure_at(int engine_failure_at) {
		this.engine_failure_at = engine_failure_at;
	}
	public Date getEngine_submission_dt() {
		return engine_submission_dt;
	}
	public void setEngine_submission_dt(Date engine_submission_dt) {
		this.engine_submission_dt = engine_submission_dt;
	}
	public Date getEngine_repair_comp_dt() {
		return engine_repair_comp_dt;
	}
	public void setEngine_repair_comp_dt(Date engine_repair_comp_dt) {
		this.engine_repair_comp_dt = engine_repair_comp_dt;
	}
	public String getAir_compressor_no() {
		return air_compressor_no;
	}
	public void setAir_compressor_no(String air_compressor_no) {
		this.air_compressor_no = air_compressor_no;
	}
	public String getAir_compressor_type() {
		return air_compressor_type;
	}
	public void setAir_compressor_type(String air_compressor_type) {
		this.air_compressor_type = air_compressor_type;
	}
	public int getAir_compressor_total_hrs() {
		return air_compressor_total_hrs;
	}
	public void setAir_compressor_total_hrs(int air_compressor_total_hrs) {
		this.air_compressor_total_hrs = air_compressor_total_hrs;
	}
	public int getAir_compressor_failure_at() {
		return air_compressor_failure_at;
	}
	public void setAir_compressor_failure_at(int air_compressor_failure_at) {
		this.air_compressor_failure_at = air_compressor_failure_at;
	}
	public Date getAir_compressor_submission_dt() {
		return air_compressor_submission_dt;
	}
	public void setAir_compressor_submission_dt(Date air_compressor_submission_dt) {
		this.air_compressor_submission_dt = air_compressor_submission_dt;
	}
	public Date getAir_compressor_repair_comp_dt() {
		return air_compressor_repair_comp_dt;
	}
	public void setAir_compressor_repair_comp_dt(Date air_compressor_repair_comp_dt) {
		this.air_compressor_repair_comp_dt = air_compressor_repair_comp_dt;
	}
	public String getSgb_no() {
		return sgb_no;
	}
	public void setSgb_no(String sgb_no) {
		this.sgb_no = sgb_no;
	}
	public String getSgb_type() {
		return sgb_type;
	}
	public void setSgb_type(String sgb_type) {
		this.sgb_type = sgb_type;
	}
	public int getSgb_total_km() {
		return sgb_total_km;
	}
	public void setSgb_total_km(int sgb_total_km) {
		this.sgb_total_km = sgb_total_km;
	}
	public Date getSgb_submission_dt() {
		return sgb_submission_dt;
	}
	public void setSgb_submission_dt(Date sgb_submission_dt) {
		this.sgb_submission_dt = sgb_submission_dt;
	}
	public Date getSgb_repair_comp_dt() {
		return sgb_repair_comp_dt;
	}
	public void setSgb_repair_comp_dt(Date sgb_repair_comp_dt) {
		this.sgb_repair_comp_dt = sgb_repair_comp_dt;
	}
	public String getIgb_no() {
		return igb_no;
	}
	public void setIgb_no(String igb_no) {
		this.igb_no = igb_no;
	}
	public String getIgb_type() {
		return igb_type;
	}
	public void setIgb_type(String igb_type) {
		this.igb_type = igb_type;
	}
	public int getIgb_total_km() {
		return igb_total_km;
	}
	public void setIgb_total_km(int igb_total_km) {
		this.igb_total_km = igb_total_km;
	}
	public Date getIgb_submission_dt() {
		return igb_submission_dt;
	}
	public void setIgb_submission_dt(Date igb_submission_dt) {
		this.igb_submission_dt = igb_submission_dt;
	}
	public Date getIgb_repair_comp_dt() {
		return igb_repair_comp_dt;
	}
	public void setIgb_repair_comp_dt(Date igb_repair_comp_dt) {
		this.igb_repair_comp_dt = igb_repair_comp_dt;
	}
	public String getTrack_assy_ser() {
		return track_assy_ser;
	}
	public void setTrack_assy_ser(String track_assy_ser) {
		this.track_assy_ser = track_assy_ser;
	}
	public int getSprocket_assy_total_km() {
		return sprocket_assy_total_km;
	}
	public void setSprocket_assy_total_km(int sprocket_assy_total_km) {
		this.sprocket_assy_total_km = sprocket_assy_total_km;
	}
	public String getSprocket_assy_ser() {
		return sprocket_assy_ser;
	}
	public void setSprocket_assy_ser(String sprocket_assy_ser) {
		this.sprocket_assy_ser = sprocket_assy_ser;
	}
	public int getPump_drive_motor_hr() {
		return pump_drive_motor_hr;
	}
	public void setPump_drive_motor_hr(int pump_drive_motor_hr) {
		this.pump_drive_motor_hr = pump_drive_motor_hr;
	}
	public String getPump_drive_motor_ser() {
		return pump_drive_motor_ser;
	}
	public void setPump_drive_motor_ser(String pump_drive_motor_ser) {
		this.pump_drive_motor_ser = pump_drive_motor_ser;
	}
	public int getStarter_genr_total_hr() {
		return starter_genr_total_hr;
	}
	public void setStarter_genr_total_hr(int starter_genr_total_hr) {
		this.starter_genr_total_hr = starter_genr_total_hr;
	}
	public String getStarter_genr_ser() {
		return starter_genr_ser;
	}
	public void setStarter_genr_ser(String starter_genr_ser) {
		this.starter_genr_ser = starter_genr_ser;
	}
	public String getTurbo_charger_type() {
		return turbo_charger_type;
	}
	public void setTurbo_charger_type(String turbo_charger_type) {
		this.turbo_charger_type = turbo_charger_type;
	}
	public int getTurbo_charger_defect_at() {
		return turbo_charger_defect_at;
	}
	public void setTurbo_charger_defect_at(int turbo_charger_defect_at) {
		this.turbo_charger_defect_at = turbo_charger_defect_at;
	}
	public Date getTurbo_charger_submission_dt() {
		return turbo_charger_submission_dt;
	}
	public void setTurbo_charger_submission_dt(Date turbo_charger_submission_dt) {
		this.turbo_charger_submission_dt = turbo_charger_submission_dt;
	}
	public Date getTurbo_charger_repair_comp_dt() {
		return turbo_charger_repair_comp_dt;
	}
	public void setTurbo_charger_repair_comp_dt(Date turbo_charger_repair_comp_dt) {
		this.turbo_charger_repair_comp_dt = turbo_charger_repair_comp_dt;
	}
	public Date getEngine_demand_dt() {
		return engine_demand_dt;
	}
	public void setEngine_demand_dt(Date engine_demand_dt) {
		this.engine_demand_dt = engine_demand_dt;
	}
	public Date getEngine_rel_dt() {
		return engine_rel_dt;
	}
	public void setEngine_rel_dt(Date engine_rel_dt) {
		this.engine_rel_dt = engine_rel_dt;
	}
	public int getFire_fight_sys_cyl_auth() {
		return fire_fight_sys_cyl_auth;
	}
	public void setFire_fight_sys_cyl_auth(int fire_fight_sys_cyl_auth) {
		this.fire_fight_sys_cyl_auth = fire_fight_sys_cyl_auth;
	}
	public int getFire_fight_sys_cyl_held() {
		return fire_fight_sys_cyl_held;
	}
	public void setFire_fight_sys_cyl_held(int fire_fight_sys_cyl_held) {
		this.fire_fight_sys_cyl_held = fire_fight_sys_cyl_held;
	}
	public int getFire_fight_sys_cyl_empty() {
		return fire_fight_sys_cyl_empty;
	}
	public void setFire_fight_sys_cyl_empty(int fire_fight_sys_cyl_empty) {
		this.fire_fight_sys_cyl_empty = fire_fight_sys_cyl_empty;
	}
	public Date getFire_fight_cyl_demand_dt() {
		return fire_fight_cyl_demand_dt;
	}
	public void setFire_fight_cyl_demand_dt(Date fire_fight_cyl_demand_dt) {
		this.fire_fight_cyl_demand_dt = fire_fight_cyl_demand_dt;
	}
	public String getCbrn_sys_over_pressure() {
		return cbrn_sys_over_pressure;
	}
	public void setCbrn_sys_over_pressure(String cbrn_sys_over_pressure) {
		this.cbrn_sys_over_pressure = cbrn_sys_over_pressure;
	}
	public int getCbrn_filter_qty() {
		return cbrn_filter_qty;
	}
	public void setCbrn_filter_qty(int cbrn_filter_qty) {
		this.cbrn_filter_qty = cbrn_filter_qty;
	}
	public String getCbrn_filter_ser() {
		return cbrn_filter_ser;
	}
	public void setCbrn_filter_ser(String cbrn_filter_ser) {
		this.cbrn_filter_ser = cbrn_filter_ser;
	}
	public String getPkuza_ser() {
		return pkuza_ser;
	}
	public void setPkuza_ser(String pkuza_ser) {
		this.pkuza_ser = pkuza_ser;
	}
	public String getBas_a_ser() {
		return bas_a_ser;
	}
	public void setBas_a_ser(String bas_a_ser) {
		this.bas_a_ser = bas_a_ser;
	}
	public String getCbrn_spl_blower_ser() {
		return cbrn_spl_blower_ser;
	}
	public void setCbrn_spl_blower_ser(String cbrn_spl_blower_ser) {
		this.cbrn_spl_blower_ser = cbrn_spl_blower_ser;
	}
	public String getMine_plough_ser() {
		return mine_plough_ser;
	}
	public void setMine_plough_ser(String mine_plough_ser) {
		this.mine_plough_ser = mine_plough_ser;
	}
	public String getEmp_ser() {
		return emp_ser;
	}
	public void setEmp_ser(String emp_ser) {
		this.emp_ser = emp_ser;
	}
	public String getMrs_ser() {
		return mrs_ser;
	}
	public void setMrs_ser(String mrs_ser) {
		this.mrs_ser = mrs_ser;
	}
	public String getDch_installed() {
		return dch_installed;
	}
	public void setDch_installed(String dch_installed) {
		this.dch_installed = dch_installed;
	}
	public String getDch_type() {
		return dch_type;
	}
	public void setDch_type(String dch_type) {
		this.dch_type = dch_type;
	}
	public Date getDch_installed_dt() {
		return dch_installed_dt;
	}
	public void setDch_installed_dt(Date dch_installed_dt) {
		this.dch_installed_dt = dch_installed_dt;
	}
	public Date getDch_vintage() {
		return dch_vintage;
	}
	public void setDch_vintage(Date dch_vintage) {
		this.dch_vintage = dch_vintage;
	}
	public String getDch_ser() {
		return dch_ser;
	}
	public void setDch_ser(String dch_ser) {
		this.dch_ser = dch_ser;
	}
	public Date getCti_install_dt() {
		return cti_install_dt;
	}
	public void setCti_install_dt(Date cti_install_dt) {
		this.cti_install_dt = cti_install_dt;
	}
	public Date getCti_vintage() {
		return cti_vintage;
	}
	public void setCti_vintage(Date cti_vintage) {
		this.cti_vintage = cti_vintage;
	}
	public String getCti_ser() {
		return cti_ser;
	}
	public void setCti_ser(String cti_ser) {
		this.cti_ser = cti_ser;
	}
	public String getMain_gun_article_no() {
		return main_gun_article_no;
	}
	public void setMain_gun_article_no(String main_gun_article_no) {
		this.main_gun_article_no = main_gun_article_no;
	}
	public String getMain_gun_article_ser() {
		return main_gun_article_ser;
	}
	public void setMain_gun_article_ser(String main_gun_article_ser) {
		this.main_gun_article_ser = main_gun_article_ser;
	}
	public int getNo_of_msls_fired() {
		return no_of_msls_fired;
	}
	public void setNo_of_msls_fired(int no_of_msls_fired) {
		this.no_of_msls_fired = no_of_msls_fired;
	}
	public int getNo_of_msls_failed() {
		return no_of_msls_failed;
	}
	public void setNo_of_msls_failed(int no_of_msls_failed) {
		this.no_of_msls_failed = no_of_msls_failed;
	}
	public String getSec_gun_regn_no() {
		return sec_gun_regn_no;
	}
	public void setSec_gun_regn_no(String sec_gun_regn_no) {
		this.sec_gun_regn_no = sec_gun_regn_no;
	}
	public String getSec_gun_ser() {
		return sec_gun_ser;
	}
	public void setSec_gun_ser(String sec_gun_ser) {
		this.sec_gun_ser = sec_gun_ser;
	}
	public String getAa_gun_type() {
		return aa_gun_type;
	}
	public void setAa_gun_type(String aa_gun_type) {
		this.aa_gun_type = aa_gun_type;
	}
	public String getAa_gun_regn_no() {
		return aa_gun_regn_no;
	}
	public void setAa_gun_regn_no(String aa_gun_regn_no) {
		this.aa_gun_regn_no = aa_gun_regn_no;
	}
	public String getAa_gun_ser() {
		return aa_gun_ser;
	}
	public void setAa_gun_ser(String aa_gun_ser) {
		this.aa_gun_ser = aa_gun_ser;
	}
	public String getMsl_launcher_regn_no() {
		return msl_launcher_regn_no;
	}
	public void setMsl_launcher_regn_no(String msl_launcher_regn_no) {
		this.msl_launcher_regn_no = msl_launcher_regn_no;
	}
	public String getMsl_launcher_ser() {
		return msl_launcher_ser;
	}
	public void setMsl_launcher_ser(String msl_launcher_ser) {
		this.msl_launcher_ser = msl_launcher_ser;
	}
	public String getMsl_launcher_oh_due() {
		return msl_launcher_oh_due;
	}
	public void setMsl_launcher_oh_due(String msl_launcher_oh_due) {
		this.msl_launcher_oh_due = msl_launcher_oh_due;
	}
	public Date getMsl_lr_oh_done_dt() {
		return msl_lr_oh_done_dt;
	}
	public void setMsl_lr_oh_done_dt(Date msl_lr_oh_done_dt) {
		this.msl_lr_oh_done_dt = msl_lr_oh_done_dt;
	}
	public Date getTisas_old_instln_dt() {
		return tisas_old_instln_dt;
	}
	public void setTisas_old_instln_dt(Date tisas_old_instln_dt) {
		this.tisas_old_instln_dt = tisas_old_instln_dt;
	}
	public Date getTisas_old_vintage() {
		return tisas_old_vintage;
	}
	public void setTisas_old_vintage(Date tisas_old_vintage) {
		this.tisas_old_vintage = tisas_old_vintage;
	}
	public String getTisas_old_ser() {
		return tisas_old_ser;
	}
	public void setTisas_old_ser(String tisas_old_ser) {
		this.tisas_old_ser = tisas_old_ser;
	}
	public Date getTisas_new_instln_dt() {
		return tisas_new_instln_dt;
	}
	public void setTisas_new_instln_dt(Date tisas_new_instln_dt) {
		this.tisas_new_instln_dt = tisas_new_instln_dt;
	}
	public Date getTisas_new_vintage() {
		return tisas_new_vintage;
	}
	public void setTisas_new_vintage(Date tisas_new_vintage) {
		this.tisas_new_vintage = tisas_new_vintage;
	}
	public String getTisas_new_ser() {
		return tisas_new_ser;
	}
	public void setTisas_new_ser(String tisas_new_ser) {
		this.tisas_new_ser = tisas_new_ser;
	}
	public String getTifcs_type() {
		return tifcs_type;
	}
	public void setTifcs_type(String tifcs_type) {
		this.tifcs_type = tifcs_type;
	}
	public String getTifcs_regn_no() {
		return tifcs_regn_no;
	}
	public void setTifcs_regn_no(String tifcs_regn_no) {
		this.tifcs_regn_no = tifcs_regn_no;
	}
	public Date getTifcs_instln_dt() {
		return tifcs_instln_dt;
	}
	public void setTifcs_instln_dt(Date tifcs_instln_dt) {
		this.tifcs_instln_dt = tifcs_instln_dt;
	}
	public Date getTifcs_vintage() {
		return tifcs_vintage;
	}
	public void setTifcs_vintage(Date tifcs_vintage) {
		this.tifcs_vintage = tifcs_vintage;
	}
	public int getRelay_kr_hr() {
		return relay_kr_hr;
	}
	public void setRelay_kr_hr(int relay_kr_hr) {
		this.relay_kr_hr = relay_kr_hr;
	}
	public String getRelay_kr_ser() {
		return relay_kr_ser;
	}
	public void setRelay_kr_ser(String relay_kr_ser) {
		this.relay_kr_ser = relay_kr_ser;
	}
	public int getLrf_hr() {
		return lrf_hr;
	}
	public void setLrf_hr(int lrf_hr) {
		this.lrf_hr = lrf_hr;
	}
	public String getLrf_ser() {
		return lrf_ser;
	}
	public void setLrf_ser(String lrf_ser) {
		this.lrf_ser = lrf_ser;
	}
	public int getGyro_direc_indi_hr() {
		return gyro_direc_indi_hr;
	}
	public void setGyro_direc_indi_hr(int gyro_direc_indi_hr) {
		this.gyro_direc_indi_hr = gyro_direc_indi_hr;
	}
	public String getGyro_direc_indi_ser() {
		return gyro_direc_indi_ser;
	}
	public void setGyro_direc_indi_ser(String gyro_direc_indi_ser) {
		this.gyro_direc_indi_ser = gyro_direc_indi_ser;
	}
	public int getDist_box_hr() {
		return dist_box_hr;
	}
	public void setDist_box_hr(int dist_box_hr) {
		this.dist_box_hr = dist_box_hr;
	}
	public String getDist_box_ser() {
		return dist_box_ser;
	}
	public void setDist_box_ser(String dist_box_ser) {
		this.dist_box_ser = dist_box_ser;
	}
	public int getDist_box_failure_at() {
		return dist_box_failure_at;
	}
	public void setDist_box_failure_at(int dist_box_failure_at) {
		this.dist_box_failure_at = dist_box_failure_at;
	}
	public String getTim_fitted() {
		return tim_fitted;
	}
	public void setTim_fitted(String tim_fitted) {
		this.tim_fitted = tim_fitted;
	}
	public Date getTim_intls_dt() {
		return tim_intls_dt;
	}
	public void setTim_intls_dt(Date tim_intls_dt) {
		this.tim_intls_dt = tim_intls_dt;
	}
	public Date getTim_vintage() {
		return tim_vintage;
	}
	public void setTim_vintage(Date tim_vintage) {
		this.tim_vintage = tim_vintage;
	}
	public String getTis_fitted() {
		return tis_fitted;
	}
	public void setTis_fitted(String tis_fitted) {
		this.tis_fitted = tis_fitted;
	}
	public Date getTis_instaln_dt() {
		return tis_instaln_dt;
	}
	public void setTis_instaln_dt(Date tis_instaln_dt) {
		this.tis_instaln_dt = tis_instaln_dt;
	}
	public Date getTis_vintage() {
		return tis_vintage;
	}
	public void setTis_vintage(Date tis_vintage) {
		this.tis_vintage = tis_vintage;
	}
	public String getU_tim_fitted() {
		return u_tim_fitted;
	}
	public void setU_tim_fitted(String u_tim_fitted) {
		this.u_tim_fitted = u_tim_fitted;
	}
	public Date getU_tim_intsln_dt() {
		return u_tim_intsln_dt;
	}
	public void setU_tim_intsln_dt(Date u_tim_intsln_dt) {
		this.u_tim_intsln_dt = u_tim_intsln_dt;
	}
	public Date getU_tim_vintage() {
		return u_tim_vintage;
	}
	public void setU_tim_vintage(Date u_tim_vintage) {
		this.u_tim_vintage = u_tim_vintage;
	}
	public String getM_tim_fitted() {
		return m_tim_fitted;
	}
	public void setM_tim_fitted(String m_tim_fitted) {
		this.m_tim_fitted = m_tim_fitted;
	}
	public Date getM_tim_instln_dt() {
		return m_tim_instln_dt;
	}
	public void setM_tim_instln_dt(Date m_tim_instln_dt) {
		this.m_tim_instln_dt = m_tim_instln_dt;
	}
	public Date getM_tim_vintage() {
		return m_tim_vintage;
	}
	public void setM_tim_vintage(Date m_tim_vintage) {
		this.m_tim_vintage = m_tim_vintage;
	}
	public String getM_tisk_fitted() {
		return m_tisk_fitted;
	}
	public void setM_tisk_fitted(String m_tisk_fitted) {
		this.m_tisk_fitted = m_tisk_fitted;
	}
	public Date getM_tisk_instals_dt() {
		return m_tisk_instals_dt;
	}
	public void setM_tisk_instals_dt(Date m_tisk_instals_dt) {
		this.m_tisk_instals_dt = m_tisk_instals_dt;
	}
	public Date getM_tisk_vintage() {
		return m_tisk_vintage;
	}
	public void setM_tisk_vintage(Date m_tisk_vintage) {
		this.m_tisk_vintage = m_tisk_vintage;
	}
	public String getDns_fitted() {
		return dns_fitted;
	}
	public void setDns_fitted(String dns_fitted) {
		this.dns_fitted = dns_fitted;
	}
	public Date getDns_instln_dt() {
		return dns_instln_dt;
	}
	public void setDns_instln_dt(Date dns_instln_dt) {
		this.dns_instln_dt = dns_instln_dt;
	}
	public String getDns_ser() {
		return dns_ser;
	}
	public void setDns_ser(String dns_ser) {
		this.dns_ser = dns_ser;
	}
	public String getCti_fitted() {
		return cti_fitted;
	}
	public void setCti_fitted(String cti_fitted) {
		this.cti_fitted = cti_fitted;
	}
	public Date getCti_instln_dt() {
		return cti_instln_dt;
	}
	public void setCti_instln_dt(Date cti_instln_dt) {
		this.cti_instln_dt = cti_instln_dt;
	}
	public String getAlns_fitted() {
		return alns_fitted;
	}
	public void setAlns_fitted(String alns_fitted) {
		this.alns_fitted = alns_fitted;
	}
	public String getAlns_ser() {
		return alns_ser;
	}
	public void setAlns_ser(String alns_ser) {
		this.alns_ser = alns_ser;
	}
	public String getGps_fitted() {
		return gps_fitted;
	}
	public void setGps_fitted(String gps_fitted) {
		this.gps_fitted = gps_fitted;
	}
	public String getGps_ser() {
		return gps_ser;
	}
	public void setGps_ser(String gps_ser) {
		this.gps_ser = gps_ser;
	}
	public String getEcu_fitted() {
		return ecu_fitted;
	}
	public void setEcu_fitted(String ecu_fitted) {
		this.ecu_fitted = ecu_fitted;
	}
	public String getEcu_ser() {
		return ecu_ser;
	}
	public void setEcu_ser(String ecu_ser) {
		this.ecu_ser = ecu_ser;
	}
	public String getSpta_fitted() {
		return spta_fitted;
	}
	public void setSpta_fitted(String spta_fitted) {
		this.spta_fitted = spta_fitted;
	}
	public String getSpta_ser() {
		return spta_ser;
	}
	public void setSpta_ser(String spta_ser) {
		this.spta_ser = spta_ser;
	}
	public int getSgb_failure_at() {
		return sgb_failure_at;
	}
	public void setSgb_failure_at(int sgb_failure_at) {
		this.sgb_failure_at = sgb_failure_at;
	}
	public int getIgb_failure_at() {
		return igb_failure_at;
	}
	public void setIgb_failure_at(int igb_failure_at) {
		this.igb_failure_at = igb_failure_at;
	}
	public int getTrack_assy_failure_at() {
		return track_assy_failure_at;
	}
	public void setTrack_assy_failure_at(int track_assy_failure_at) {
		this.track_assy_failure_at = track_assy_failure_at;
	}
	public int getSprocket_assy_failure_at() {
		return sprocket_assy_failure_at;
	}
	public void setSprocket_assy_failure_at(int sprocket_assy_failure_at) {
		this.sprocket_assy_failure_at = sprocket_assy_failure_at;
	}
	public int getPump_drive_motor_failure_at() {
		return pump_drive_motor_failure_at;
	}
	public void setPump_drive_motor_failure_at(int pump_drive_motor_failure_at) {
		this.pump_drive_motor_failure_at = pump_drive_motor_failure_at;
	}
	public int getStarter_genr_failure_at() {
		return starter_genr_failure_at;
	}
	public void setStarter_genr_failure_at(int starter_genr_failure_at) {
		this.starter_genr_failure_at = starter_genr_failure_at;
	}
	public int getRelay_kr_failure_at() {
		return relay_kr_failure_at;
	}
	public void setRelay_kr_failure_at(int relay_kr_failure_at) {
		this.relay_kr_failure_at = relay_kr_failure_at;
	}
	public int getLrf_failure_at() {
		return lrf_failure_at;
	}
	public void setLrf_failure_at(int lrf_failure_at) {
		this.lrf_failure_at = lrf_failure_at;
	}
	public int getGyro_direc_failure_at() {
		return gyro_direc_failure_at;
	}
	public void setGyro_direc_failure_at(int gyro_direc_failure_at) {
		this.gyro_direc_failure_at = gyro_direc_failure_at;
	}
	public String getMrs_type() {
		return mrs_type;
	}
	public void setMrs_type(String mrs_type) {
		this.mrs_type = mrs_type;
	}
	public int getMrs_qty() {
		return mrs_qty;
	}
	public void setMrs_qty(int mrs_qty) {
		this.mrs_qty = mrs_qty;
	}
	public String getType_gun_article() {
		return type_gun_article;
	}
	public void setType_gun_article(String type_gun_article) {
		this.type_gun_article = type_gun_article;
	}
	public String getMain_gun_article_qtr() {
		return main_gun_article_qtr;
	}
	public void setMain_gun_article_qtr(String main_gun_article_qtr) {
		this.main_gun_article_qtr = main_gun_article_qtr;
	}
	public int getMain_gun_article_efc() {
		return main_gun_article_efc;
	}
	public void setMain_gun_article_efc(int main_gun_article_efc) {
		this.main_gun_article_efc = main_gun_article_efc;
	}
	public String getPowerpack_ser() {
		return powerpack_ser;
	}
	public void setPowerpack_ser(String powerpack_ser) {
		this.powerpack_ser = powerpack_ser;
	}
	public Date getPowerpack_demand_dt() {
		return powerpack_demand_dt;
	}
	public void setPowerpack_demand_dt(Date powerpack_demand_dt) {
		this.powerpack_demand_dt = powerpack_demand_dt;
	}
	public String getStab_ser() {
		return stab_ser;
	}
	public void setStab_ser(String stab_ser) {
		this.stab_ser = stab_ser;
	}
	public Date getStab_demand_dt() {
		return stab_demand_dt;
	}
	public void setStab_demand_dt(Date stab_demand_dt) {
		this.stab_demand_dt = stab_demand_dt;
	}
	public String getIfdss_ser() {
		return ifdss_ser;
	}
	public void setIfdss_ser(String ifdss_ser) {
		this.ifdss_ser = ifdss_ser;
	}
	public Date getIfdss_demand_dt() {
		return ifdss_demand_dt;
	}
	public void setIfdss_demand_dt(Date ifdss_demand_dt) {
		this.ifdss_demand_dt = ifdss_demand_dt;
	}
	public String getGms_ser() {
		return gms_ser;
	}
	public void setGms_ser(String gms_ser) {
		this.gms_ser = gms_ser;
	}
	public String getGms_demand_dtcps_mk_ser() {
		return gms_demand_dtcps_mk_ser;
	}
	public void setGms_demand_dtcps_mk_ser(String gms_demand_dtcps_mk_ser) {
		this.gms_demand_dtcps_mk_ser = gms_demand_dtcps_mk_ser;
	}
	public Date getCps_mk_demand_dt() {
		return cps_mk_demand_dt;
	}
	public void setCps_mk_demand_dt(Date cps_mk_demand_dt) {
		this.cps_mk_demand_dt = cps_mk_demand_dt;
	}
	public String getDvr_pnvd_ser() {
		return dvr_pnvd_ser;
	}
	public void setDvr_pnvd_ser(String dvr_pnvd_ser) {
		this.dvr_pnvd_ser = dvr_pnvd_ser;
	}
	public Date getDvr_pnvd_demand_dt() {
		return dvr_pnvd_demand_dt;
	}
	public void setDvr_pnvd_demand_dt(Date dvr_pnvd_demand_dt) {
		this.dvr_pnvd_demand_dt = dvr_pnvd_demand_dt;
	}
	public String getFcc_ser() {
		return fcc_ser;
	}
	public void setFcc_ser(String fcc_ser) {
		this.fcc_ser = fcc_ser;
	}
	public Date getFcc_demand_dt() {
		return fcc_demand_dt;
	}
	public void setFcc_demand_dt(Date fcc_demand_dt) {
		this.fcc_demand_dt = fcc_demand_dt;
	}
	public int getBogie_wheels_held_onrd() {
		return bogie_wheels_held_onrd;
	}
	public void setBogie_wheels_held_onrd(int bogie_wheels_held_onrd) {
		this.bogie_wheels_held_onrd = bogie_wheels_held_onrd;
	}
	public int getBogie_wheels_held_offrd() {
		return bogie_wheels_held_offrd;
	}
	public void setBogie_wheels_held_offrd(int bogie_wheels_held_offrd) {
		this.bogie_wheels_held_offrd = bogie_wheels_held_offrd;
	}
	public Date getBogie_wheels_demand_dt() {
		return bogie_wheels_demand_dt;
	}
	public void setBogie_wheels_demand_dt(Date bogie_wheels_demand_dt) {
		this.bogie_wheels_demand_dt = bogie_wheels_demand_dt;
	}
	public String getTop_roll_ser() {
		return top_roll_ser;
	}
	public void setTop_roll_ser(String top_roll_ser) {
		this.top_roll_ser = top_roll_ser;
	}
	public Date getTop_roll_demand_dt() {
		return top_roll_demand_dt;
	}
	public void setTop_roll_demand_dt(Date top_roll_demand_dt) {
		this.top_roll_demand_dt = top_roll_demand_dt;
	}
	public int getTrack_pads_held_onrd() {
		return track_pads_held_onrd;
	}
	public void setTrack_pads_held_onrd(int track_pads_held_onrd) {
		this.track_pads_held_onrd = track_pads_held_onrd;
	}
	public int getTrack_pads_held_offrd() {
		return track_pads_held_offrd;
	}
	public void setTrack_pads_held_offrd(int track_pads_held_offrd) {
		this.track_pads_held_offrd = track_pads_held_offrd;
	}
	public Date getTrack_pads_demand_dt() {
		return track_pads_demand_dt;
	}
	public void setTrack_pads_demand_dt(Date track_pads_demand_dt) {
		this.track_pads_demand_dt = track_pads_demand_dt;
	}
	public String getCdr_cont_stn_ser() {
		return cdr_cont_stn_ser;
	}
	public void setCdr_cont_stn_ser(String cdr_cont_stn_ser) {
		this.cdr_cont_stn_ser = cdr_cont_stn_ser;
	}
	public Date getCdr_cont_stn_demmand_dt() {
		return cdr_cont_stn_demmand_dt;
	}
	public void setCdr_cont_stn_demmand_dt(Date cdr_cont_stn_demmand_dt) {
		this.cdr_cont_stn_demmand_dt = cdr_cont_stn_demmand_dt;
	}
	public String getGcdu_ser() {
		return gcdu_ser;
	}
	public void setGcdu_ser(String gcdu_ser) {
		this.gcdu_ser = gcdu_ser;
	}
	public Date getGcdu_demand_dt() {
		return gcdu_demand_dt;
	}
	public void setGcdu_demand_dt(Date gcdu_demand_dt) {
		this.gcdu_demand_dt = gcdu_demand_dt;
	}
	
	
	  

	public String getLow_tempered_bal() {
		return low_tempered_bal;
	}
	public void setLow_tempered_bal(String low_tempered_bal) {
		this.low_tempered_bal = low_tempered_bal;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}

}
