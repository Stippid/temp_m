package com.model.Animal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tb_animal_training_history", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
public class TB_ANIMAL_TRAINING_HISTORY {
	
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
	private String trainer_sus;
    private String trainer_perno;
    private String trainer_name;
    private String trainer_rank;
    private Integer trg_status = 0;
    private Integer exam_status = 0;
    private Long trainer_mobno = 0L; // Changed to Long
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date training_from_date;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date training_to_date;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date approved_dt;
	private String approved_by;
	private String sus_no;
	private String tester_sus;
	private String tester_perno;
	private String tester_name;
	private String tester_rank;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date validate_date;
	private String tester_remark;
	private String trg_remark;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	public Long getTrainer_mobno() {
		return trainer_mobno;
	}
	public void setTrainer_mobno(Long trainer_mobno) {
		this.trainer_mobno = trainer_mobno;
	}
	public Date getTraining_from_date() {
		return training_from_date;
	}
	public void setTraining_from_date(Date training_from_date) {
		this.training_from_date = training_from_date;
	}
	public Date getTraining_to_date() {
		return training_to_date;
	}
	public void setTraining_to_date(Date training_to_date) {
		this.training_to_date = training_to_date;
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
	public String getTrainer_sus() {
		return trainer_sus;
	}
	public void setTrainer_sus(String trainer_sus) {
		this.trainer_sus = trainer_sus;
	}
	public String getTrainer_perno() {
		return trainer_perno;
	}
	public void setTrainer_perno(String trainer_perno) {
		this.trainer_perno = trainer_perno;
	}
	public Integer getTrg_status() {
		return trg_status;
	}
	public void setTrg_status(Integer trg_status) {
		this.trg_status = trg_status;
	}
	public Integer getExam_status() {
		return exam_status;
	}
	public void setExam_status(Integer exam_status) {
		this.exam_status = exam_status;
	}
	public String getTester_sus() {
		return tester_sus;
	}
	public void setTester_sus(String tester_sus) {
		this.tester_sus = tester_sus;
	}
	public String getTester_perno() {
		return tester_perno;
	}
	public void setTester_perno(String tester_perno) {
		this.tester_perno = tester_perno;
	}
	public Date getValidate_date() {
		return validate_date;
	}
	public void setValidate_date(Date validate_date) {
		this.validate_date = validate_date;
	}
	public String getTester_remark() {
		return tester_remark;
	}
	public void setTester_remark(String tester_remark) {
		this.tester_remark = tester_remark;
	}
	public String getTrg_remark() {
		return trg_remark;
	}
	public void setTrg_remark(String trg_remark) {
		this.trg_remark = trg_remark;
	}
	public String getTrainer_name() {
		return trainer_name;
	}
	public void setTrainer_name(String trainer_name) {
		this.trainer_name = trainer_name;
	}
	public String getTrainer_rank() {
		return trainer_rank;
	}
	public void setTrainer_rank(String trainer_rank) {
		this.trainer_rank = trainer_rank;
	}
	public String getTester_name() {
		return tester_name;
	}
	public void setTester_name(String tester_name) {
		this.tester_name = tester_name;
	}
	public String getTester_rank() {
		return tester_rank;
	}
	public void setTester_rank(String tester_rank) {
		this.tester_rank = tester_rank;
	}
	


}
