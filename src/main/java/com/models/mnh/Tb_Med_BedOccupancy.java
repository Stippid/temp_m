package com.models.mnh;

import static javax.persistence.GenerationType.IDENTITY;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_med_bedoccupancy", uniqueConstraints = {
@UniqueConstraint(columnNames = "id")})
public class Tb_Med_BedOccupancy {
	private int id;
	private String sus_no;
	private Date bo_date;
	private String category;
	private String ward;
	private String bed_occupied;
	private String needy_admission;
	private String number_refused;
	private String refusal_reason;
	private String rejection_reason;
	private String record_status;
	private Date created_on;
    private String created_by;
    private Date modified_on;
    private String modified_by;
    private Date apprvd_at_unit_on;
    private String apprvr_at_unit_by;
    private Date apprvd_at_miso_on;
    private String apprvr_at_miso_by;
    private Date time_stamp;
    
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
	public Date getBo_date() {
		return bo_date;
	}
	public void setBo_date(Date bo_date) {
		this.bo_date = bo_date;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	public String getBed_occupied() {
		return bed_occupied;
	}
	public void setBed_occupied(String bed_occupied) {
		this.bed_occupied = bed_occupied;
	}
	public String getNeedy_admission() {
		return needy_admission;
	}
	public void setNeedy_admission(String needy_admission) {
		this.needy_admission = needy_admission;
	}
	public String getNumber_refused() {
		return number_refused;
	}
	public void setNumber_refused(String number_refused) {
		this.number_refused = number_refused;
	}
	public String getRefusal_reason() {
		return refusal_reason;
	}
	public void setRefusal_reason(String refusal_reason) {
		this.refusal_reason = refusal_reason;
	}
	public String getRejection_reason() {
		return rejection_reason;
	}
	public void setRejection_reason(String rejection_reason) {
		this.rejection_reason = rejection_reason;
	}
	public String getRecord_status() {
		return record_status;
	}
	public void setRecord_status(String record_status) {
		this.record_status = record_status;
	}
	public Date getCreated_on() {
		return created_on;
	}
	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public Date getModified_on() {
		return modified_on;
	}
	public void setModified_on(Date modified_on) {
		this.modified_on = modified_on;
	}
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	public Date getApprvd_at_unit_on() {
		return apprvd_at_unit_on;
	}
	public void setApprvd_at_unit_on(Date apprvd_at_unit_on) {
		this.apprvd_at_unit_on = apprvd_at_unit_on;
	}
	public String getApprvr_at_unit_by() {
		return apprvr_at_unit_by;
	}
	public void setApprvr_at_unit_by(String apprvr_at_unit_by) {
		this.apprvr_at_unit_by = apprvr_at_unit_by;
	}
	public Date getApprvd_at_miso_on() {
		return apprvd_at_miso_on;
	}
	public void setApprvd_at_miso_on(Date apprvd_at_miso_on) {
		this.apprvd_at_miso_on = apprvd_at_miso_on;
	}
	public String getApprvr_at_miso_by() {
		return apprvr_at_miso_by;
	}
	public void setApprvr_at_miso_by(String apprvr_at_miso_by) {
		this.apprvr_at_miso_by = apprvr_at_miso_by;
	}
	public Date getTime_stamp() {
		return time_stamp;
	}
	public void setTime_stamp(Date time_stamp) {
		this.time_stamp = time_stamp;
	}
}