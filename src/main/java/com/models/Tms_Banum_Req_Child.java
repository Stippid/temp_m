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
@Table(name = "tb_tms_banum_req_child", uniqueConstraints = {
@UniqueConstraint(columnNames = "parent_req_id")})
public class Tms_Banum_Req_Child {

      private int parent_req_id; 
	  private String engine_no; 
	  private String chasis_no;
	  private String  tech_spec; 
	  private String vehicle_clas_code; 
	  private BigInteger mct_number; 
	  private String type_of_fuel;
	  
	  @DateTimeFormat(pattern = "yyyy")
	  private Date  year_of_entry; 
	  
	  private int  no_of_wheels; 
	  private int no_of_axles; 
	  private String  axle_wts; 
	  private String  drive; 
	  private String spl_eqpmnt_fitd; 
	  private int tonnage; 
	  private int towing_capcty; 
	  private int lift_capcty; 
	  private String  typ_of_spl_eqpt_role; 
	  private String  veh_class; 
	  private String  sponsoring_dte; 
	  private String auth_letter_no; 
	  private String  old_ba_no; 
	  private String  convrsn_done; 
	  private String  new_nomencatre;
	  private String  sr_no_pencl_rubngs; 
	  private String  sr_no_photogrph; 
	  private String  remarks; 
	  private String  request_status; 
	  private String  appr_rej_remarks; 
	  private String  approved_by; 
	  
	  @DateTimeFormat(pattern = "yyyy-MM-dd")
	  private Date   approve_date;
	  
	  private String  creation_by; 
	  private Date creation_date; 
	  private String  modify_by; 
	  
	  @DateTimeFormat(pattern = "yyyy-MM-dd")
	  private Date modify_date;
	  
	  private String deleted_by;
	  
	  @DateTimeFormat(pattern = "yyyy-MM-dd")
	  private Date deleted_date;
	  
	  private int version_no; 
	  private String  softdelete; 
	  private String country_of_origin; 
	  
	  private String quantity; 
	  private String  ba_no;
	  private String veh_cat;
	  
	  @DateTimeFormat(pattern = "yyyy-MM-dd")
	  private Date date_of_auth_letter;
	  
	  private String initiating_auth;
	  private String engine_image;
	  private String chasis_img; 
	 // private byte chasis_image;
	  private String front_view_image;
	  private String back_view_image;
	  private String side_view_image;
	  private String top_view_image;
	  private int id;
	  private String purchas_cost;

	private String present_cost;
	  
	private String wheel_track ;
	private String no_of_bogie_wheel ;
	 // private float purchas_cost1;
	//  private float present_cost1;
	  
	 
	
	 
	  
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getParent_req_id() {
		return parent_req_id;
	}
	public void setParent_req_id(int parent_req_id) {
		this.parent_req_id = parent_req_id;
	}
	public String getEngine_no() {
		return engine_no;
	}
	public void setEngine_no(String engine_no) {
		this.engine_no = engine_no;
	}
	public String getChasis_no() {
		return chasis_no;
	}
	public void setChasis_no(String chasis_no) {
		this.chasis_no = chasis_no;
	}
	public String getTech_spec() {
		return tech_spec;
	}
	public void setTech_spec(String tech_spec) {
		this.tech_spec = tech_spec;
	}
	public String getVehicle_clas_code() {
		return vehicle_clas_code;
	}
	public void setVehicle_clas_code(String vehicle_clas_code) {
		this.vehicle_clas_code = vehicle_clas_code;
	}
	public BigInteger getMct_number() {
		return mct_number;
	}
	public void setMct_number(BigInteger mct_number) {
		this.mct_number = mct_number;
	}
	public String getType_of_fuel() {
		return type_of_fuel;
	}
	public void setType_of_fuel(String type_of_fuel) {
		this.type_of_fuel = type_of_fuel;
	}
	public Date getYear_of_entry() {
		return year_of_entry;
	}
	public void setYear_of_entry(Date year_of_entry) {
		this.year_of_entry = year_of_entry;
	}
	public int getNo_of_wheels() {
		return no_of_wheels;
	}
	public void setNo_of_wheels(int no_of_wheels) {
		this.no_of_wheels = no_of_wheels;
	}
	public int getNo_of_axles() {
		return no_of_axles;
	}
	public void setNo_of_axles(int no_of_axles) {
		this.no_of_axles = no_of_axles;
	}
	public String getAxle_wts() {
		return axle_wts;
	}
	public void setAxle_wts(String axle_wts) {
		this.axle_wts = axle_wts;
	}
	public String getDrive() {
		return drive;
	}
	public void setDrive(String drive) {
		this.drive = drive;
	}
	public String getSpl_eqpmnt_fitd() {
		return spl_eqpmnt_fitd;
	}
	public void setSpl_eqpmnt_fitd(String spl_eqpmnt_fitd) {
		this.spl_eqpmnt_fitd = spl_eqpmnt_fitd;
	}
	public int getTonnage() {
		return tonnage;
	}
	public void setTonnage(int tonnage) {
		this.tonnage = tonnage;
	}
	public int getTowing_capcty() {
		return towing_capcty;
	}
	public void setTowing_capcty(int towing_capcty) {
		this.towing_capcty = towing_capcty;
	}
	public int getLift_capcty() {
		return lift_capcty;
	}
	public void setLift_capcty(int lift_capcty) {
		this.lift_capcty = lift_capcty;
	}
	public String getTyp_of_spl_eqpt_role() {
		return typ_of_spl_eqpt_role;
	}
	public void setTyp_of_spl_eqpt_role(String typ_of_spl_eqpt_role) {
		this.typ_of_spl_eqpt_role = typ_of_spl_eqpt_role;
	}
	public String getVeh_class() {
		return veh_class;
	}
	public void setVeh_class(String veh_class) {
		this.veh_class = veh_class;
	}
	public String getSponsoring_dte() {
		return sponsoring_dte;
	}
	public void setSponsoring_dte(String sponsoring_dte) {
		this.sponsoring_dte = sponsoring_dte;
	}
	public String getAuth_letter_no() {
		return auth_letter_no;
	}
	public void setAuth_letter_no(String auth_letter_no) {
		this.auth_letter_no = auth_letter_no;
	}
	public String getOld_ba_no() {
		return old_ba_no;
	}
	public void setOld_ba_no(String old_ba_no) {
		this.old_ba_no = old_ba_no;
	}
	public String getConvrsn_done() {
		return convrsn_done;
	}
	public void setConvrsn_done(String convrsn_done) {
		this.convrsn_done = convrsn_done;
	}
	public String getNew_nomencatre() {
		return new_nomencatre;
	}
	public void setNew_nomencatre(String new_nomencatre) {
		this.new_nomencatre = new_nomencatre;
	}
	public String getSr_no_pencl_rubngs() {
		return sr_no_pencl_rubngs;
	}
	public void setSr_no_pencl_rubngs(String sr_no_pencl_rubngs) {
		this.sr_no_pencl_rubngs = sr_no_pencl_rubngs;
	}
	public String getSr_no_photogrph() {
		return sr_no_photogrph;
	}
	public void setSr_no_photogrph(String sr_no_photogrph) {
		this.sr_no_photogrph = sr_no_photogrph;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getRequest_status() {
		return request_status;
	}
	public void setRequest_status(String request_status) {
		this.request_status = request_status;
	}
	public String getAppr_rej_remarks() {
		return appr_rej_remarks;
	}
	public void setAppr_rej_remarks(String appr_rej_remarks) {
		this.appr_rej_remarks = appr_rej_remarks;
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
	public String getCountry_of_origin() {
		return country_of_origin;
	}
	public void setCountry_of_origin(String country_of_origin) {
		this.country_of_origin = country_of_origin;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getBa_no() {
		return ba_no;
	}
	public void setBa_no(String ba_no) {
		this.ba_no = ba_no;
	}
	public String getVeh_cat() {
		return veh_cat;
	}
	public void setVeh_cat(String veh_cat) {
		this.veh_cat = veh_cat;
	}
	public Date getDate_of_auth_letter() {
		return date_of_auth_letter;
	}
	public void setDate_of_auth_letter(Date date_of_auth_letter) {
		this.date_of_auth_letter = date_of_auth_letter;
	}
	public String getInitiating_auth() {
		return initiating_auth;
	}
	public void setInitiating_auth(String initiating_auth) {
		this.initiating_auth = initiating_auth;
	}
	public String getEngine_image() {
		return engine_image;
	}
	public void setEngine_image(String engine_image) {
		this.engine_image = engine_image;
	}
	
	public String getFront_view_image() {
		return front_view_image;
	}
	public void setFront_view_image(String front_view_image) {
		this.front_view_image = front_view_image;
	}
	public String getBack_view_image() {
		return back_view_image;
	}
	public void setBack_view_image(String back_view_image) {
		this.back_view_image = back_view_image;
	}
	public String getSide_view_image() {
		return side_view_image;
	}
	public void setSide_view_image(String side_view_image) {
		this.side_view_image = side_view_image;
	}
	public String getTop_view_image() {
		return top_view_image;
	}
	public void setTop_view_image(String top_view_image) {
		this.top_view_image = top_view_image;
	}
	public String getPresent_cost() {
		return present_cost;
	}
	public void setPresent_cost(String present_cost) {
		this.present_cost = present_cost;
	}
	public String getPurchas_cost() {
		return purchas_cost;
	}
	public void setPurchas_cost(String purchas_cost) {
		this.purchas_cost = purchas_cost;
	}
	public String getChasis_img() {
		return chasis_img;
	}
	public void setChasis_img(String chasis_img) {
		this.chasis_img = chasis_img;
	}
	public String getWheel_track() {
		return wheel_track;
	}
	public void setWheel_track(String wheel_track) {
		this.wheel_track = wheel_track;
	}
	public String getNo_of_bogie_wheel() {
		return no_of_bogie_wheel;
	}
	public void setNo_of_bogie_wheel(String no_of_bogie_wheel) {
		this.no_of_bogie_wheel = no_of_bogie_wheel;
	}
	 
/*	public float getPurchas_cost1() {
		return purchas_cost1;
	}
	public void setPurchas_cost1(float purchas_cost1) {
		this.purchas_cost1 = purchas_cost1;
	}
	public float getPresent_cost1() {
		return present_cost1;
	}
	public void setPresent_cost1(float present_cost1) {
		this.present_cost1 = present_cost1;
	}*/
	
	/*public byte getChasis_image() {
		return chasis_image;
	}
	public void setChasis_image(byte chasis_image) {
		this.chasis_image = chasis_image;
	}*/
	/*public String getChasis_image() {
		return chasis_image;
	}
	public void setChasis_image(String chasis_image) {
		this.chasis_image = chasis_image;
	}*/
	/*public int getPresent_cost() {
		return present_cost;
	}
	public void setPresent_cost(int present_cost) {
		this.present_cost = present_cost;
	}*/
}
