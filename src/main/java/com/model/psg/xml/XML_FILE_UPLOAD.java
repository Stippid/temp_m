package com.model.psg.xml;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;

@Entity
@Table(name = "xml_files_upload_new")
public class XML_FILE_UPLOAD {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

	@Column(name = "file_name", nullable = false)
	private String fileName;

	@Column(name = "army_no", nullable = false)
	private String armyNo;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "file_data", columnDefinition = "BYTEA")
	private byte[] fileData;

	@Column(name = "uploaded_status", nullable = false)
	private String uploadedStatus;

	@Column(name = "error_msg", nullable = false)
	private String errorMsg;

	@Column(name = "uploaded_on", columnDefinition = "TIMESTAMPTZ DEFAULT current_timestamp")
	private Timestamp uploadedOn;

	@Column(name = "status", nullable = false)
	private int status;

	@Column(name = "comm_id", nullable = false)
	private BigInteger comm_id;

	@Column(name = "present_p2_no", nullable = false)
	private String present_p2_no;

	@Column(name = "modified_by")
	private String modified_by;

	@Column(name = "modified_date", columnDefinition = "TIMESTAMPTZ DEFAULT current_timestamp")
	private Timestamp modified_date;

	@Column(name = "census_id", nullable = false)
	private int census_id;

	@Column(name = "sus_no")
	private String sus_no;
	
	@Column(name = "unit_name")
	private String unit_name;
	
	@Column(name = "cda_account_no")
	private String cda_account_no;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
 	private Date present_p2_date;

	@Column(name = "rank")
	private String rank;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getArmyNo() {
		return armyNo;
	}

	public void setArmyNo(String armyNo) {
		this.armyNo = armyNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

	public String getUploadedStatus() {
		return uploadedStatus;
	}

	public void setUploadedStatus(String uploadedStatus) {
		this.uploadedStatus = uploadedStatus;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Timestamp getUploadedOn() {
		return uploadedOn;
	}

	public void setUploadedOn(Timestamp uploadedOn) {
		this.uploadedOn = uploadedOn;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public BigInteger getComm_id() {
		return comm_id;
	}

	public void setComm_id(BigInteger comm_id) {
		this.comm_id = comm_id;
	}

	public String getPresent_p2_no() {
		return present_p2_no;
	}

	public void setPresent_p2_no(String present_p2_no) {
		this.present_p2_no = present_p2_no;
	}

	public String getModified_by() {
		return modified_by;
	}

	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}

	public Timestamp getModified_date() {
		return modified_date;
	}

	public void setModified_date(Timestamp modified_date) {
		this.modified_date = modified_date;
	}

	public int getCensus_id() {
		return census_id;
	}

	public void setCensus_id(int census_id) {
		this.census_id = census_id;
	}

	public String getSus_no() {
		return sus_no;
	}

	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}

	public String getUnit_name() {
		return unit_name;
	}

	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}

	public String getCda_account_no() {
		return cda_account_no;
	}

	public void setCda_account_no(String cda_account_no) {
		this.cda_account_no = cda_account_no;
	}

	public Date getPresent_p2_date() {
		return present_p2_date;
	}

	public void setPresent_p2_date(Date present_p2_date) {
		this.present_p2_date = present_p2_date;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public XML_FILE_UPLOAD() {
		super();
		// TODO Auto-generated constructor stub
	}

	public XML_FILE_UPLOAD(Long id, String fileName, String armyNo, String name, byte[] fileData, String uploadedStatus,
			String errorMsg, Timestamp uploadedOn, int status, BigInteger comm_id, String present_p2_no,
			String modified_by, Timestamp modified_date, int census_id, String sus_no, String unit_name,
			String cda_account_no, Date present_p2_date, String rank) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.armyNo = armyNo;
		this.name = name;
		this.fileData = fileData;
		this.uploadedStatus = uploadedStatus;
		this.errorMsg = errorMsg;
		this.uploadedOn = uploadedOn;
		this.status = status;
		this.comm_id = comm_id;
		this.present_p2_no = present_p2_no;
		this.modified_by = modified_by;
		this.modified_date = modified_date;
		this.census_id = census_id;
		this.sus_no = sus_no;
		this.unit_name = unit_name;
		this.cda_account_no = cda_account_no;
		this.present_p2_date = present_p2_date;
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "XML_FILE_UPLOAD [id=" + id + ", fileName=" + fileName + ", armyNo=" + armyNo + ", name=" + name
				+ ", fileData=" + Arrays.toString(fileData) + ", uploadedStatus=" + uploadedStatus + ", errorMsg="
				+ errorMsg + ", uploadedOn=" + uploadedOn + ", status=" + status + ", comm_id=" + comm_id
				+ ", present_p2_no=" + present_p2_no + ", modified_by=" + modified_by + ", modified_date="
				+ modified_date + ", census_id=" + census_id + ", sus_no=" + sus_no + ", unit_name=" + unit_name
				+ ", cda_account_no=" + cda_account_no + ", present_p2_date=" + present_p2_date + ", rank=" + rank
				+ "]";
	}

    
	
    
}
