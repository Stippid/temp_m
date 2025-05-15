package com.models;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "cue_tb_miso_mms_wet_pet_det", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class CUE_TB_MISO_MMS_WET_PET_DET {
	private int id;
	private String  vettedby_dte1;	 
	private String  vettedby_dte2;	
	private String  wet_pet_no;
	private String  cos_sec;
	private String  item_equip_name;
	private String  item_seq_no;
	private String  acc_unit;
	private String  periodicity;
	//private BigInteger  authorization;
	private double  authorization;
	private String  ces_cces_no;
	private Double  version_no;
	private String  softdelete;
	private String  created_by;
	private String  created_on;
	private String  modified_by;
	private String  modified_on;
	private String  cec_cces;
	private String  equip_table_id;
	private String  catpart_id;
	private String  catpart_no;
	private String  wet_type;
	private String  remarks;
	private String status;
	private int roleid;
	private String  reject_remarks;
	private String  reject_letter_no;
	private String  reject_date;
	
	  public int getRoleid() {
			return roleid;
		}
		public void setRoleid(int roleid) {
			this.roleid = roleid;
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
	public String getVettedby_dte1() {
		return vettedby_dte1;
	}
	public void setVettedby_dte1(String vettedby_dte1) {
		this.vettedby_dte1 = vettedby_dte1;
	}
	public String getVettedby_dte2() {
		return vettedby_dte2;
	}
	public void setVettedby_dte2(String vettedby_dte2) {
		this.vettedby_dte2 = vettedby_dte2;
	}
	public String getWet_pet_no() {
		return wet_pet_no;
	}
	public void setWet_pet_no(String wet_pet_no) {
		this.wet_pet_no = wet_pet_no;
	}
	public String getCos_sec() {
		return cos_sec;
	}
	public void setCos_sec(String cos_sec) {
		this.cos_sec = cos_sec;
	}
	public String getItem_equip_name() {
		return item_equip_name;
	}
	public void setItem_equip_name(String item_equip_name) {
		this.item_equip_name = item_equip_name;
	}
	public String getItem_seq_no() {
		return item_seq_no;
	}
	public void setItem_seq_no(String item_seq_no) {
		this.item_seq_no = item_seq_no;
	}
	public String getAcc_unit() {
		return acc_unit;
	}
	public void setAcc_unit(String acc_unit) {
		this.acc_unit = acc_unit;
	}
	public String getPeriodicity() {
		return periodicity;
	}
	public void setPeriodicity(String periodicity) {
		this.periodicity = periodicity;
	}
	
	
	
	@Column(name = "\"authorization\"")
	public double getAuthorization() {
		return authorization;
	}
	public void setAuthorization(double authorization) {
		this.authorization = authorization;
	}
	
	public String getCes_cces_no() {
		return ces_cces_no;
	}
	public void setCes_cces_no(String ces_cces_no) {
		this.ces_cces_no = ces_cces_no;
	}
	public Double getVersion_no() {
		return version_no;
	}
	public void setVersion_no(Double version_no) {
		this.version_no = version_no;
	}
	public String getSoftdelete() {
		return softdelete;
	}
	public void setSoftdelete(String softdelete) {
		this.softdelete = softdelete;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public String getCreated_on() {
		return created_on;
	}
	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	public String getModified_on() {
		return modified_on;
	}
	public void setModified_on(String modified_on) {
		this.modified_on = modified_on;
	}
	public String getCec_cces() {
		return cec_cces;
	}
	public void setCec_cces(String cec_cces) {
		this.cec_cces = cec_cces;
	}
	public String getEquip_table_id() {
		return equip_table_id;
	}
	public void setEquip_table_id(String equip_table_id) {
		this.equip_table_id = equip_table_id;
	}
	public String getCatpart_id() {
		return catpart_id;
	}
	public void setCatpart_id(String catpart_id) {
		this.catpart_id = catpart_id;
	}
	public String getCatpart_no() {
		return catpart_no;
	}
	public void setCatpart_no(String catpart_no) {
		this.catpart_no = catpart_no;
	}
	public String getWet_type() {
		return wet_type;
	}
	public void setWet_type(String wet_type) {
		this.wet_type = wet_type;
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


public String getReject_remarks() {
		return reject_remarks;
	}
	public void setReject_remarks(String reject_remarks) {
		this.reject_remarks = reject_remarks;
	}
	public String getReject_letter_no() {
		return reject_letter_no;
	}
	public void setReject_letter_no(String reject_letter_no) {
		this.reject_letter_no = reject_letter_no;
	}
	public String getReject_date() {
		return reject_date;
	}
	public void setReject_date(String reject_date) {
		this.reject_date = reject_date;
	}

	

}
