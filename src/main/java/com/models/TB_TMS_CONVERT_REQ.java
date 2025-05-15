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
@Table(name = "tb_tms_convert_req", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class TB_TMS_CONVERT_REQ {
	
	
	private int id ;
	private int   userid ;
	private String  sus_no ;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dte_of_reqst ;
	
	private String  engine_no ;
	private String   chasis_no ;
	private String  tech_spec ;
	private String  vehicle_class_code ;
	private BigInteger   old_mct_number ;
	private String   auth_letter_no ;
	private String   old_ba_no ;
	private String   convrsn_done ;
	private String   new_nomencatre ;
	private BigInteger  new_mct_number ;
	private String  remarks ;
	private String  status ;
	private String   appr_rej_remarks;
	private String   approved_by ;
	@DateTimeFormat(pattern = "dd-mm-yyyy")
	private Date   approve_date ;
	private String   creation_by;
	@DateTimeFormat(pattern = "dd-mm-yyyy")
	private Date   creation_date ;
	private String   modify_by ;
	@DateTimeFormat(pattern = "dd-mm-yyyy")
	private Date   modify_date ;
	private String   deleted_by ;
	@DateTimeFormat(pattern = "dd-mm-yyyy")
	private Date   deleted_date ;
	private Integer   version_no ;
	private String   softdelete ;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date  received_from ;
	private String   sponsoring_dte ;
	private String   requesting_agency ;
	
	
	private String   justification ;
	private String   financial_impact ;
	
	private String new_veh_images;
	
	private String   conv_modCarriedOut ;
	private String   newstdnomencltr_veh_aftrmod_as_auth_we ;
	
	 private String front_view_image;
	 private String back_view_image;
	 private String side_view_image;
	 private String top_view_image;
	 private String new_ba_no ;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getSus_no() {
		return sus_no;
	}
	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
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
	public String getVehicle_class_code() {
		return vehicle_class_code;
	}
	public void setVehicle_class_code(String vehicle_class_code) {
		this.vehicle_class_code = vehicle_class_code;
	}
	public BigInteger getOld_mct_number() {
		return old_mct_number;
	}
	public void setOld_mct_number(BigInteger old_mct_number) {
		this.old_mct_number = old_mct_number;
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
	public BigInteger getNew_mct_number() {
		return new_mct_number;
	}
	public void setNew_mct_number(BigInteger new_mct_number) {
		this.new_mct_number = new_mct_number;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getCreation_by() {
		return creation_by;
	}
	public void setCreation_by(String creation_by) {
		this.creation_by = creation_by;
	}
	public String getModify_by() {
		return modify_by;
	}
	public void setModify_by(String modify_by) {
		this.modify_by = modify_by;
	}
	public String getDeleted_by() {
		return deleted_by;
	}
	public void setDeleted_by(String deleted_by) {
		this.deleted_by = deleted_by;
	}
	public Integer getVersion_no() {
		return version_no;
	}
	public void setVersion_no(Integer version_no) {
		this.version_no = version_no;
	}
	public String getSoftdelete() {
		return softdelete;
	}
	public void setSoftdelete(String softdelete) {
		this.softdelete = softdelete;
	}
	
	@DateTimeFormat(pattern = "dd-mm-yyyy")
	public Date getReceived_from() {
		return received_from;
	}
	public void setReceived_from(Date received_from) {
		this.received_from = received_from;
	}
	public String getSponsoring_dte() {
		return sponsoring_dte;
	}
	public void setSponsoring_dte(String sponsoring_dte) {
		this.sponsoring_dte = sponsoring_dte;
	}
	public String getRequesting_agency() {
		return requesting_agency;
	}
	public void setRequesting_agency(String requesting_agency) {
		this.requesting_agency = requesting_agency;
	}

	
	public Date getDte_of_reqst() {
		return dte_of_reqst;
	}
	public void setDte_of_reqst(Date dte_of_reqst) {
		this.dte_of_reqst = dte_of_reqst;
	}
	
	
	public Date getApprove_date() {
		return approve_date;
	}
	public void setApprove_date(Date approve_date) {
		this.approve_date = approve_date;
	}
	
	
	public Date getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}
	
	
	public Date getModify_date() {
		return modify_date;
	}
	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}

	
	public Date getDeleted_date() {
		return deleted_date;
	}
	public void setDeleted_date(Date deleted_date) {
		this.deleted_date = deleted_date;
	}
	public String getJustification() {
		return justification;
	}
	public void setJustification(String justification) {
		this.justification = justification;
	}
	public String getFinancial_impact() {
		return financial_impact;
	}
	public void setFinancial_impact(String financial_impact) {
		this.financial_impact = financial_impact;
	}
	public String getConv_modCarriedOut() {
		return conv_modCarriedOut;
	}
	public void setConv_modCarriedOut(String conv_modCarriedOut) {
		this.conv_modCarriedOut = conv_modCarriedOut;
	}
	public String getNewstdnomencltr_veh_aftrmod_as_auth_we() {
		return newstdnomencltr_veh_aftrmod_as_auth_we;
	}
	public void setNewstdnomencltr_veh_aftrmod_as_auth_we(String newstdnomencltr_veh_aftrmod_as_auth_we) {
		this.newstdnomencltr_veh_aftrmod_as_auth_we = newstdnomencltr_veh_aftrmod_as_auth_we;
	}
	public String getNew_veh_images() {
		return new_veh_images;
	}
	public void setNew_veh_images(String new_veh_images) {
		this.new_veh_images = new_veh_images;
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
	public String getNew_ba_no() {
		return new_ba_no;
	}
	public void setNew_ba_no(String new_ba_no) {
		this.new_ba_no = new_ba_no;
	}
}
