package com.models;
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
@Table(name = "mms_tb_child_regn_mstr_detl", uniqueConstraints = {
@UniqueConstraint(columnNames = "id")})
public class MMS_TB_CHILD_REGN_MSTR_DETL {

	
	  private int id;	  
	  private String sus_no; 
	  private String type_of_hldg;
	  private String type_of_eqpt;
	  private String from_sus_no;
	  private String to_sus_no;
	  private String op_status;
	  private String tfr_status;
	  private Date from_tr_date; 
	  private Date to_tr_date;
	  private String data_upd_by;
	  private Date data_upd_date;
	  private String TFileName;
	  private String census_no;
	  private String nFMNo;
	  private String nOldval;
	  private String nTMNo;
	  private String nNewV;
	  private String AuthType;
	  private String fname; 	 
	  private String rv_no;
	  private String regn_seq_no;
	  private String eqpt_regn_no;
	  private String to_hld_type;
	  private String to_eqpt_type;
	  private String nPara;
	  private String nFileName;
	  private String username;
	  private int status;
	  private String nxt_level_in_hierarchy_sus_no;
	 // @DateTimeFormat(pattern = "yyyy-MM-dd")
	  private String rv_date;
	  private String prf_code;
	  private int depres_dur_year;
	  
	  
	  
	  
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

	public String getCensus_no() {
		return census_no;
	}
	public void setCensus_no(String census_no) {
		this.census_no = census_no;
	}
	public String getType_of_hldg() {
		return type_of_hldg;
	}
	public void setType_of_hldg(String type_of_hldg) {
		this.type_of_hldg = type_of_hldg;
	}
	public String getType_of_eqpt() {
		return type_of_eqpt;
	}
	public void setType_of_eqpt(String type_of_eqpt) {
		this.type_of_eqpt = type_of_eqpt;
	}

	public String getFrom_sus_no() {
		return from_sus_no;
	}
	public void setFrom_sus_no(String from_sus_no) {
		this.from_sus_no = from_sus_no;
	}

	public Date getFrom_tr_date() {
		return from_tr_date;
	}
	public void setFrom_tr_date(Date from_tr_date) {
		this.from_tr_date = from_tr_date;
	}
	public String getTo_sus_no() {
		return to_sus_no;
	}
	public void setTo_sus_no(String to_sus_no) {
		this.to_sus_no = to_sus_no;
	}

	public Date getTo_tr_date() {
		return to_tr_date;
	}
	public void setTo_tr_date(Date to_tr_date) {
		this.to_tr_date = to_tr_date;
	}

	public String getData_upd_by() {
		return data_upd_by;
	}
	public void setData_upd_by(String data_upd_by) {
		this.data_upd_by = data_upd_by;
	}
	public Date getData_upd_date() {
		return data_upd_date;
	}
	public void setData_upd_date(Date data_upd_date) {
		this.data_upd_date = data_upd_date;
	}

	public String getOp_status() {
		return op_status;
	}
	public void setOp_status(String op_status) {
		this.op_status = op_status;
	}
	public String getTfr_status() {
		return tfr_status;
	}
	public void setTfr_status(String tfr_status) {
		this.tfr_status = tfr_status;
	}
	public String getRv_no() {
		return rv_no;
	}
	public void setRv_no(String rv_no) {
		this.rv_no = rv_no;
	}

	public String getPrf_code() {
		return prf_code;
	}
	public void setPrf_code(String prf_code) {
		this.prf_code = prf_code;
	}
	public int getDepres_dur_year() {
		return depres_dur_year;
	}
	public void setDepres_dur_year(int depres_dur_year) {
		this.depres_dur_year = depres_dur_year;
	}
	public String getTFileName() {
		return TFileName;
	}
	public void setTFileName(String tFileName) {
		TFileName = tFileName;
	}
	public String getnFMNo() {
		return nFMNo;
	}
	public void setnFMNo(String nFMNo) {
		this.nFMNo = nFMNo;
	}
	public String getnOldval() {
		return nOldval;
	}
	public void setnOldval(String nOldval) {
		this.nOldval = nOldval;
	}
	public String getnTMNo() {
		return nTMNo;
	}
	public void setnTMNo(String nTMNo) {
		this.nTMNo = nTMNo;
	}
	public String getnNewV() {
		return nNewV;
	}
	public void setnNewV(String nNewV) {
		this.nNewV = nNewV;
	}
	public String getAuthType() {
		return AuthType;
	}
	public void setAuthType(String authType) {
		AuthType = authType;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getRegn_seq_no() {
		return regn_seq_no;
	}
	public void setRegn_seq_no(String regn_seq_no) {
		this.regn_seq_no = regn_seq_no;
	}
	public String getEqpt_regn_no() {
		return eqpt_regn_no;
	}
	public void setEqpt_regn_no(String eqpt_regn_no) {
		this.eqpt_regn_no = eqpt_regn_no;
	}
	public String getTo_hld_type() {
		return to_hld_type;
	}
	public void setTo_hld_type(String to_hld_type) {
		this.to_hld_type = to_hld_type;
	}
	public String getTo_eqpt_type() {
		return to_eqpt_type;
	}
	public void setTo_eqpt_type(String to_eqpt_type) {
		this.to_eqpt_type = to_eqpt_type;
	}
	public String getnPara() {
		return nPara;
	}
	public void setnPara(String nPara) {
		this.nPara = nPara;
	}
	public String getnFileName() {
		return nFileName;
	}
	public void setnFileName(String nFileName) {
		this.nFileName = nFileName;
	}
	public String getRv_date() {
		return rv_date;
	}
	public void setRv_date(String rv_date) {
		this.rv_date = rv_date;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getNxt_level_in_hierarchy_sus_no() {
		return nxt_level_in_hierarchy_sus_no;
	}
	public void setNxt_level_in_hierarchy_sus_no(String nxt_level_in_hierarchy_sus_no) {
		this.nxt_level_in_hierarchy_sus_no = nxt_level_in_hierarchy_sus_no;
	}

	
}
