package com.models.mnh;
import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_med_hiv", uniqueConstraints = {
@UniqueConstraint(columnNames = "id"),})
public class Tb_Med_HIV {
	private int id ;
	private String sus_no;
	private String accession_no;
	private String persnl_no;
	private String category;
	//private int rank;
	private String persnl_name;
	private String relation;
	private String service;
	private String persnl_unit;
	//private String persnl_unit_desc;
	private String reasons_elisa_screening;
	private String other_details_elisa_screening;
	private String source_infection;
	private String possible_place_siwsw;
	private String remarks;
	private String name_confirmatory_test;
	private String name_confirming_center;
	private String lab_reference_no;
	private String rejection_reason;
	private String record_status;
	private String created_by;
	private String modified_by;
	private String apprvr_at_unit_by;
	private String apprvr_at_miso_by;
	private String command1;
	private String sex;
	private String marital_status;
	private String unit;
	private String station;
	private String other_details_source_infection;
	//private String unit_name;
	private Date possible_date_siwsw;
	private Date report_date;
	private Date date_of_birth;
	
	private Date report_received_on;
	private Date created_on;
	private Date modified_on;
	private Date apprvd_at_unit_on;
	private Date apprvd_at_miso_on;
	private int persnl_age;
	private int total_service;
	private String type;
	private BigInteger adhar_card_no;
	private BigInteger contact_no;
	
    @Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
    public int getId() {
    	return id;
    }
    public void setId(int id) {
    	this.id = id;
    }
    @Column(length = 8)
	public String getSus_no() {
		return sus_no;
	}
	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}
	@Column(length = 30)
	public String getAccession_no() {
		return accession_no;
	}
	public void setAccession_no(String accession_no) {
		this.accession_no = accession_no;
	}
	@Column(length = 20)
	public String getPersnl_no() {
		return persnl_no;
	}
	public void setPersnl_no(String persnl_no) {
		this.persnl_no = persnl_no;
	}
	@Column(length = 10)
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	@Column(length = 100)
	public String getPersnl_name() {
		return persnl_name;
	}
	public void setPersnl_name(String persnl_name) {
		this.persnl_name = persnl_name;
	}
	@Column(length = 10)
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	@Column(length = 30)
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	@Column(length = 150)
	public String getPersnl_unit() {
		return persnl_unit;
	}
	public void setPersnl_unit(String persnl_unit) {
		this.persnl_unit = persnl_unit;
	}

	/*
	 * public String getPersnl_unit_desc() { return persnl_unit_desc; } public void
	 * setPersnl_unit_desc(String persnl_unit_desc) { this.persnl_unit_desc =
	 * persnl_unit_desc; }
	 */
	@Column(length = 200)
	public String getReasons_elisa_screening() {
		return reasons_elisa_screening;
	}
	public void setReasons_elisa_screening(String reasons_elisa_screening) {
		this.reasons_elisa_screening = reasons_elisa_screening;
	}
	@Column(length = 250)
	public String getOther_details_elisa_screening() {
		return other_details_elisa_screening;
	}
	public void setOther_details_elisa_screening(String other_details_elisa_screening) {
		this.other_details_elisa_screening = other_details_elisa_screening;
	}
	@Column(length = 200)
	public String getSource_infection() {
		return source_infection;
	}
	public void setSource_infection(String source_infection) {
		this.source_infection = source_infection;
	}
	@Column(length = 100)
	public String getPossible_place_siwsw() {
		return possible_place_siwsw;
	}
	public void setPossible_place_siwsw(String possible_place_siwsw) {
		this.possible_place_siwsw = possible_place_siwsw;
	}
	@Column(length = 250)
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Date getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	@Column(length = 20)
	public String getName_confirmatory_test() {
		return name_confirmatory_test;
	}
	public void setName_confirmatory_test(String name_confirmatory_test) {
		this.name_confirmatory_test = name_confirmatory_test;
	}
	@Column(length = 100)
	public String getName_confirming_center() {
		return name_confirming_center;
	}
	public void setName_confirming_center(String name_confirming_center) {
		this.name_confirming_center = name_confirming_center;
	}
	@Column(length = 40)
	public String getLab_reference_no() {
		return lab_reference_no;
	}
	public void setLab_reference_no(String lab_reference_no) {
		this.lab_reference_no = lab_reference_no;
	}
	@Column(length = 250)
	public String getRejection_reason() {
		return rejection_reason;
	}
	public void setRejection_reason(String rejection_reason) {
		this.rejection_reason = rejection_reason;
	}
	@Column(length = 10)
	public String getRecord_status() {
		return record_status;
	}
	public void setRecord_status(String record_status) {
		this.record_status = record_status;
	}
	@Column(length = 35)
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	@Column(length = 35)
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	@Column(length = 35)
	public String getApprvr_at_unit_by() {
		return apprvr_at_unit_by;
	}
	public void setApprvr_at_unit_by(String apprvr_at_unit_by) {
		this.apprvr_at_unit_by = apprvr_at_unit_by;
	}
	@Column(length = 35)
	public String getApprvr_at_miso_by() {
		return apprvr_at_miso_by;
	}
	public void setApprvr_at_miso_by(String apprvr_at_miso_by) {
		this.apprvr_at_miso_by = apprvr_at_miso_by;
	}

	/*
	 * public String getCommand1() { return command1; } public void
	 * setCommand1(String command1) { this.command1 = command1; }
	 */
	@Column(length = 10)
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Column(length = 10)
	public String getMarital_status() {
		return marital_status;
	}
	public void setMarital_status(String marital_status) {
		this.marital_status = marital_status;
	}
	@Column(length = 100)
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	@Column(length = 50)
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
	@Column(length = 250)
	public String getOther_details_source_infection() {
		return other_details_source_infection;
	}
	public void setOther_details_source_infection(String other_details_source_infection) {
		this.other_details_source_infection = other_details_source_infection;
	}

	/*
	 * public String getUnit_name() { return unit_name; } public void
	 * setUnit_name(String unit_name) { this.unit_name = unit_name; }
	 */
	public Date getPossible_date_siwsw() {
		return possible_date_siwsw;
	}
	public void setPossible_date_siwsw(Date possible_date_siwsw) {
		this.possible_date_siwsw = possible_date_siwsw;
	}
	public Date getReport_date() {
		return report_date;
	}
	public void setReport_date(Date report_date) {
		this.report_date = report_date;
	}
	public Date getReport_received_on() {
		return report_received_on;
	}
	public void setReport_received_on(Date report_received_on) {
		this.report_received_on = report_received_on;
	}
	public Date getCreated_on() {
		return created_on;
	}
	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}
	public Date getModified_on() {
		return modified_on;
	}
	public void setModified_on(Date modified_on) {
		this.modified_on = modified_on;
	}
	public Date getApprvd_at_unit_on() {
		return apprvd_at_unit_on;
	}
	public void setApprvd_at_unit_on(Date apprvd_at_unit_on) {
		this.apprvd_at_unit_on = apprvd_at_unit_on;
	}
	public Date getApprvd_at_miso_on() {
		return apprvd_at_miso_on;
	}
	public void setApprvd_at_miso_on(Date apprvd_at_miso_on) {
		this.apprvd_at_miso_on = apprvd_at_miso_on;
	}
	public int getPersnl_age() {
		return persnl_age;
	}
	public void setPersnl_age(int persnl_age) {
		this.persnl_age = persnl_age;
	}
	public int getTotal_service() {
		return total_service;
	}
	public void setTotal_service(int total_service) {
		this.total_service = total_service;
	}
	@Column(length = 15)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCommand1() {
		return command1;
	}
	public void setCommand1(String command1) {
		this.command1 = command1;
	}
	public BigInteger getAdhar_card_no() {
		return adhar_card_no;
	}
	public void setAdhar_card_no(BigInteger adhar_card_no) {
		this.adhar_card_no = adhar_card_no;
	}
	public BigInteger getContact_no() {
		return contact_no;
	}
	public void setContact_no(BigInteger contact_no) {
		this.contact_no = contact_no;
	}
	
	
	

    private Tb_Med_RankCode hivaid_rank;
   @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rank", nullable = false)
	public Tb_Med_RankCode getHivaid_rank() {
		return hivaid_rank;
	}
	public void setHivaid_rank(Tb_Med_RankCode hivaid_rank) {
		this.hivaid_rank = hivaid_rank;
	}
	
}