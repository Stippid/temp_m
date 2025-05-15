package com.model.Animal;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tb_animal_utilization_history", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
public class TB_ANIMAL_UTILIZATION_HISTORY {
	
	private int id;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date ason_Date;
	private int census_id=0;
	private String created_by;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date created_date;
	private String modified_by;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date modified_date;
	private int status=0;
    private String remarks ;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date approved_dt;
	private String approved_by;
	private String sus_no;
	private Double total_rop = 0.0;
    private Integer no_of_veh = 0;
    private Integer sanitisation_duty = 0;
    private Integer v_duties = 0;
    private Double area_kms = 0.0;
    private Integer detected_explosive = 0;
    private Integer no_of_article = 0;
    private Integer hideout_Enemy = 0;
    private Integer no_of_arms = 0;
    private Integer no_of_reftrg = 0;
    private Integer no_of_shbo = 0;
    private Integer ptr_km = 0;
    private Integer detected_mine = 0;
    private Integer total_guard = 0;
    private Integer detected_debris = 0;
    private Integer detected_avalnches = 0;
    private Integer ops_room = 0;
    private Integer ops_killed = 0;
    private Integer ops_apprehended = 0;
    private Integer detected_escape = 0;
    private Integer esitrep_status=0;
    private String  esitrep_num;
    private String doc_path;
    
    
    
    @Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getAson_Date() {
		return ason_Date;
	}
	public void setAson_Date(Date ason_Date) {
		this.ason_Date = ason_Date;
	}
	public int getCensus_id() {
		return census_id;
	}
	public void setCensus_id(int census_id) {
		this.census_id = census_id;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Date getApproved_dt() {
		return approved_dt;
	}
	public void setApproved_dt(Date approved_dt) {
		this.approved_dt = approved_dt;
	}
	public String getApproved_by() {
		return approved_by;
	}
	public void setApproved_by(String approved_by) {
		this.approved_by = approved_by;
	}
	public String getSus_no() {
		return sus_no;
	}
	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}
	public Double getTotal_rop() {
		return total_rop;
	}
	public void setTotal_rop(Double total_rop) {
		this.total_rop = total_rop;
	}
	public Integer getNo_of_veh() {
		return no_of_veh;
	}
	public void setNo_of_veh(Integer no_of_veh) {
		this.no_of_veh = no_of_veh;
	}
	public Integer getSanitisation_duty() {
		return sanitisation_duty;
	}
	public void setSanitisation_duty(Integer sanitisation_duty) {
		this.sanitisation_duty = sanitisation_duty;
	}
	public Integer getV_duties() {
		return v_duties;
	}
	public void setV_duties(Integer v_duties) {
		this.v_duties = v_duties;
	}
	public Double getArea_kms() {
		return area_kms;
	}
	public void setArea_kms(Double area_kms) {
		this.area_kms = area_kms;
	}
	public Integer getDetected_explosive() {
		return detected_explosive;
	}
	public void setDetected_explosive(Integer detected_explosive) {
		this.detected_explosive = detected_explosive;
	}
	public Integer getNo_of_article() {
		return no_of_article;
	}
	public void setNo_of_article(Integer no_of_article) {
		this.no_of_article = no_of_article;
	}
	public Integer getHideout_Enemy() {
		return hideout_Enemy;
	}
	public void setHideout_Enemy(Integer hideout_Enemy) {
		this.hideout_Enemy = hideout_Enemy;
	}
	public Integer getNo_of_arms() {
		return no_of_arms;
	}
	public void setNo_of_arms(Integer no_of_arms) {
		this.no_of_arms = no_of_arms;
	}
	public Integer getNo_of_reftrg() {
		return no_of_reftrg;
	}
	public void setNo_of_reftrg(Integer no_of_reftrg) {
		this.no_of_reftrg = no_of_reftrg;
	}
	public Integer getNo_of_shbo() {
		return no_of_shbo;
	}
	public void setNo_of_shbo(Integer no_of_shbo) {
		this.no_of_shbo = no_of_shbo;
	}
	public Integer getPtr_km() {
		return ptr_km;
	}
	public void setPtr_km(Integer ptr_km) {
		this.ptr_km = ptr_km;
	}
	public Integer getDetected_mine() {
		return detected_mine;
	}
	public void setDetected_mine(Integer detected_mine) {
		this.detected_mine = detected_mine;
	}
	public Integer getTotal_guard() {
		return total_guard;
	}
	public void setTotal_guard(Integer total_guard) {
		this.total_guard = total_guard;
	}
	public Integer getDetected_debris() {
		return detected_debris;
	}
	public void setDetected_debris(Integer detected_debris) {
		this.detected_debris = detected_debris;
	}
	public Integer getDetected_avalnches() {
		return detected_avalnches;
	}
	public void setDetected_avalnches(Integer detected_avalnches) {
		this.detected_avalnches = detected_avalnches;
	}
	public Integer getOps_room() {
		return ops_room;
	}
	public void setOps_room(Integer ops_room) {
		this.ops_room = ops_room;
	}
	public Integer getOps_killed() {
		return ops_killed;
	}
	public void setOps_killed(Integer ops_killed) {
		this.ops_killed = ops_killed;
	}
	public Integer getOps_apprehended() {
		return ops_apprehended;
	}
	public void setOps_apprehended(Integer ops_apprehended) {
		this.ops_apprehended = ops_apprehended;
	}
	public Integer getDetected_escape() {
		return detected_escape;
	}
	public void setDetected_escape(Integer detected_escape) {
		this.detected_escape = detected_escape;
	}
	public Integer getEsitrep_status() {
		return esitrep_status;
	}
	public void setEsitrep_status(Integer esitrep_status) {
		this.esitrep_status = esitrep_status;
	}
	public String getEsitrep_num() {
		return esitrep_num;
	}
	public void setEsitrep_num(String esitrep_num) {
		this.esitrep_num = esitrep_num;
	}
	public String getDoc_path() {
		return doc_path;
	}
	public void setDoc_path(String doc_path) {
		this.doc_path = doc_path;
	}
	
    
}
