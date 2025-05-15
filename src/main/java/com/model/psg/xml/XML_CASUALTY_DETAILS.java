package com.model.psg.xml;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "xml_casualty_details")
public class XML_CASUALTY_DETAILS {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
	
	 @Column(name = "xml_file_upload_id", nullable = false)
private Long xml_file_upload_id;

    @Column(name = "CasSeqNo")
    private String CasSeqNo;

    @Column(name = "personnel_no")
    private String personnel_no;

    @Column(name = "status")
    private int status;

    @Column(name = "casualty_no")
    private int casualty_no;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getXml_file_upload_id() {
		return xml_file_upload_id;
	}

	public void setXml_file_upload_id(Long xml_file_upload_id) {
		this.xml_file_upload_id = xml_file_upload_id;
	}

	public String getCasSeqNo() {
		return CasSeqNo;
	}

	public void setCasSeqNo(String casSeqNo) {
		CasSeqNo = casSeqNo;
	}

	public String getPersonnel_no() {
		return personnel_no;
	}

	public void setPersonnel_no(String personnel_no) {
		this.personnel_no = personnel_no;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCasualty_no() {
		return casualty_no;
	}

	public void setCasualty_no(int casualty_no) {
		this.casualty_no = casualty_no;
	}

	@Override
	public String toString() {
		return "XML_CASUALTY_DETAILS [id=" + id + ", xml_file_upload_id=" + xml_file_upload_id + ", CasSeqNo="
				+ CasSeqNo + ", personnel_no=" + personnel_no + ", status=" + status + ", casualty_no=" + casualty_no
				+ "]";
	}

	public XML_CASUALTY_DETAILS(Long id, Long xml_file_upload_id, String casSeqNo, String personnel_no, int status,
			int casualty_no) {
		super();
		this.id = id;
		this.xml_file_upload_id = xml_file_upload_id;
		CasSeqNo = casSeqNo;
		this.personnel_no = personnel_no;
		this.status = status;
		this.casualty_no = casualty_no;
	}

	public XML_CASUALTY_DETAILS() {
		super();
		// TODO Auto-generated constructor stub
	}
	
    
  
	   

}
