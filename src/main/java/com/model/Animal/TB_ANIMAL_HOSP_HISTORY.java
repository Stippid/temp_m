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
@Table(name = "tb_animal_hosp_history", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
public class TB_ANIMAL_HOSP_HISTORY {
	
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
	private int hosp_name = 0;
    private double hemoglobin = 0;
    private double weight = 0;
    private double tlc = 0;
    private double dlc = 0;
    private double trbc = 0;
    private double pcv = 0;
    private double platelet = 0;
    private double sgot = 0;
    private double bill = 0;
    private double urine = 0;
    private double protein = 0;
    private double creatinine = 0;
    private double albun = 0;
    private double agr = 0;
    private double bun = 0;
    private double stool = 0;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date approved_dt;
	private String approved_by;
	private String sus_no;
	private String stool_remaks;
	private String urine_remakrs;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date med_date;
	
	public Date getMed_date() {
		return med_date;
	}


	public void setMed_date(Date med_date) {
		this.med_date = med_date;
	}


	public String getSus_no() {
		return sus_no;
	}


	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
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
	public int getHosp_name() {
		return hosp_name;
	}
	public void setHosp_name(int hosp_name) {
		this.hosp_name = hosp_name;
	}
	public double getHemoglobin() {
		return hemoglobin;
	}
	public void setHemoglobin(double hemoglobin) {
		this.hemoglobin = hemoglobin;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getTlc() {
		return tlc;
	}
	public void setTlc(double tlc) {
		this.tlc = tlc;
	}
	public double getDlc() {
		return dlc;
	}
	public void setDlc(double dlc) {
		this.dlc = dlc;
	}
	public double getTrbc() {
		return trbc;
	}
	public void setTrbc(double trbc) {
		this.trbc = trbc;
	}
	public double getPcv() {
		return pcv;
	}
	public void setPcv(double pcv) {
		this.pcv = pcv;
	}
	public double getPlatelet() {
		return platelet;
	}
	public void setPlatelet(double platelet) {
		this.platelet = platelet;
	}
	public double getSgot() {
		return sgot;
	}
	public void setSgot(double sgot) {
		this.sgot = sgot;
	}
	public double getBill() {
		return bill;
	}
	public void setBill(double bill) {
		this.bill = bill;
	}
	public double getUrine() {
		return urine;
	}
	public void setUrine(double urine) {
		this.urine = urine;
	}
	public double getProtein() {
		return protein;
	}
	public void setProtein(double protein) {
		this.protein = protein;
	}
	public double getCreatinine() {
		return creatinine;
	}
	public void setCreatinine(double creatinine) {
		this.creatinine = creatinine;
	}
	public double getAlbun() {
		return albun;
	}
	public void setAlbun(double albun) {
		this.albun = albun;
	}
	public double getAgr() {
		return agr;
	}
	public void setAgr(double agr) {
		this.agr = agr;
	}
	public double getBun() {
		return bun;
	}
	public void setBun(double bun) {
		this.bun = bun;
	}
	public double getStool() {
		return stool;
	}
	public void setStool(double stool) {
		this.stool = stool;
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


	public String getStool_remaks() {
		return stool_remaks;
	}


	public void setStool_remaks(String stool_remaks) {
		this.stool_remaks = stool_remaks;
	}


	public String getUrine_remakrs() {
		return urine_remakrs;
	}


	public void setUrine_remakrs(String urine_remakrs) {
		this.urine_remakrs = urine_remakrs;
	}
	
    
	
	

}
