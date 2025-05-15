package com.models;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "mms_tb_regn_engr_mstr" , uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class MMS_TB_REGN_ENGR_MSTR {
	
	private int id;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id",unique = true, nullable = false)
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}

	private String stores_type;
	@Column(name = "stores_type")
	public String getStores_type()
	{
		return stores_type;
	}
	public void setStores_type(String stores_type)
	{
		this.stores_type = stores_type;
	}

	private String saction_auth;
	@Column(name = "saction_auth")
	public String getSaction_auth()
	{
		return saction_auth;
	}
	public void setSaction_auth(String saction_auth)
	{
		this.saction_auth = saction_auth;
	}

	private String govt_sanction_no;
	@Column(name = "govt_sanction_no")
	public String getGovt_sanction_no()
	{
		return govt_sanction_no;
	}
	public void setGovt_sanction_no(String govt_sanction_no)
	{
		this.govt_sanction_no = govt_sanction_no;
	}

	private String auth_letter_no;
	@Column(name = "auth_letter_no")
	public String getAuth_letter_no()
	{
		return auth_letter_no;
	}
	public void setAuth_letter_no(String auth_letter_no)
	{
		this.auth_letter_no = auth_letter_no;
	}

	private String auth_date;
	@Column(name = "auth_date")
	public String getAuth_date()
	{
		return auth_date;
	}
	public void setAuth_date(String auth_date)
	{
		this.auth_date = auth_date;
	}

	private String issued_from;
	@Column(name = "issued_from")
	public String getIssued_from()
	{
		return issued_from;
	}
	public void setIssued_from(String issued_from)
	{
		this.issued_from = issued_from;
	}

	private String iv_sus_no;
	@Column(name = "iv_sus_no")
	public String getIv_sus_no()
	{
		return iv_sus_no;
	}
	public void setIv_sus_no(String iv_sus_no)
	{
		this.iv_sus_no = iv_sus_no;
	}

	private String iv_no;
	@Column(name = "iv_no")
	public String getIv_no()
	{
		return iv_no;
	}
	public void setIv_no(String iv_no)
	{
		this.iv_no = iv_no;
	}

	private String iv_date;
	@Column(name = "iv_date")
	public String getIv_date()
	{
		return iv_date;
	}
	public void setIv_date(String iv_date)
	{
		this.iv_date = iv_date;
	}

	private String loan_expiry_date;
	@Column(name = "loan_expiry_date")
	public String getLoan_expiry_date()
	{
		return loan_expiry_date;
	}
	public void setLoan_expiry_date(String loan_expiry_date)
	{
		this.loan_expiry_date = loan_expiry_date;
	}

	private String prf_code;
	@Column(name = "prf_code")
	public String getPrf_code()
	{
		return prf_code;
	}
	public void setPrf_code(String prf_code)
	{
		this.prf_code = prf_code;
	}

	private String census_no;
	@Column(name = "census_no")
	public String getCensus_no()
	{
		return census_no;
	}
	public void setCensus_no(String census_no)
	{
		this.census_no = census_no;
	}

	private String type_of_hldg;
	@Column(name = "type_of_hldg")
	public String getType_of_hldg()
	{
		return type_of_hldg;
	}
	public void setType_of_hldg(String type_of_hldg)
	{
		this.type_of_hldg = type_of_hldg;
	}

	private String type_of_eqpt;
	@Column(name = "type_of_eqpt")
	public String getType_of_eqpt()
	{
		return type_of_eqpt;
	}
	public void setType_of_eqpt(String type_of_eqpt)
	{
		this.type_of_eqpt = type_of_eqpt;
	}

	private int qty;
	@Column(name = "qty")
	public int getQty()
	{
		return qty;
	}
	public void setQty(int qty)
	{
		this.qty = qty;
	}

	private String eqpt_regn_no;
	@Column(name = "eqpt_regn_no")
	public String getEqpt_regn_no()
	{
		return eqpt_regn_no;
	}
	public void setEqpt_regn_no(String eqpt_regn_no)
	{
		this.eqpt_regn_no = eqpt_regn_no;
	}

	private String regn_seq_no;
	@Column(name = "regn_seq_no")
	public String getRegn_seq_no()
	{
		return regn_seq_no;
	}
	public void setRegn_seq_no(String regn_seq_no)
	{
		this.regn_seq_no = regn_seq_no;
	}

	private String from_sus_no;
	@Column(name = "from_sus_no")
	public String getFrom_sus_no()
	{
		return from_sus_no;
	}
	public void setFrom_sus_no(String from_sus_no)
	{
		this.from_sus_no = from_sus_no;
	}

	private String from_form_code;
	@Column(name = "from_form_code")
	public String getFrom_form_code()
	{
		return from_form_code;
	}
	public void setFrom_form_code(String from_form_code)
	{
		this.from_form_code = from_form_code;
	}

	private Date from_tr_date;
	@Column(name = "from_tr_date")
	public Date getFrom_tr_date()
	{
		return from_tr_date;
	}
	public void setFrom_tr_date(Date from_tr_date)
	{
		this.from_tr_date = from_tr_date;
	}

	private String to_sus_no;
	@Column(name = "to_sus_no")
	public String getTo_sus_no()
	{
		return to_sus_no;
	}
	public void setTo_sus_no(String to_sus_no)
	{
		this.to_sus_no = to_sus_no;
	}

	private String to_form_code;
	@Column(name = "to_form_code")
	public String getTo_form_code()
	{
		return to_form_code;
	}
	public void setTo_form_code(String to_form_code)
	{
		this.to_form_code = to_form_code;
	}

	private Date to_tr_date;
	@Column(name = "to_tr_date")
	public Date getTo_tr_date()
	{
		return to_tr_date;
	}
	public void setTo_tr_date(Date to_tr_date)
	{
		this.to_tr_date = to_tr_date;
	}

	private String service_status;
	@Column(name = "service_status")
	public String getService_status()
	{
		return service_status;
	}
	public void setService_status(String service_status)
	{
		this.service_status = service_status;
	}

	private String spl_remarks;
	@Column(name = "spl_remarks")
	public String getSpl_remarks()
	{
		return spl_remarks;
	}
	public void setSpl_remarks(String spl_remarks)
	{
		this.spl_remarks = spl_remarks;
	}

	private String remarks;
	@Column(name = "remarks")
	public String getRemarks()
	{
		return remarks;
	}
	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}

	private String data_cr_by;
	@Column(name = "data_cr_by")
	public String getData_cr_by()
	{
		return data_cr_by;
	}
	public void setData_cr_by(String data_cr_by)
	{
		this.data_cr_by = data_cr_by;
	}

	private Date data_cr_date;
	@Column(name = "data_cr_date")
	public Date getData_cr_date()
	{
		return data_cr_date;
	}
	public void setData_cr_date(Date data_cr_date)
	{
		this.data_cr_date = data_cr_date;
	}

	private String data_upd_by;
	@Column(name = "data_upd_by")
	public String getData_upd_by()
	{
		return data_upd_by;
	}
	public void setData_upd_by(String data_upd_by)
	{
		this.data_upd_by = data_upd_by;
	}

	private Date data_upd_date;
	@Column(name = "data_upd_date")
	public Date getData_upd_date()
	{
		return data_upd_date;
	}
	public void setData_upd_date(Date data_upd_date)
	{
		this.data_upd_date = data_upd_date;
	}

	private String data_app_by;
	@Column(name = "data_app_by")
	public String getData_app_by()
	{
		return data_app_by;
	}
	public void setData_app_by(String data_app_by)
	{
		this.data_app_by = data_app_by;
	}

	private Date data_app_date;
	@Column(name = "data_app_date")
	public Date getData_app_date()
	{
		return data_app_date;
	}
	public void setData_app_date(Date data_app_date)
	{
		this.data_app_date = data_app_date;
	}

	private String op_status;
	@Column(name = "op_status")
	public String getOp_status()
	{
		return op_status;
	}
	public void setOp_status(String op_status)
	{
		this.op_status = op_status;
	}

	private String tfr_status;
	@Column(name = "tfr_status")
	public String getTfr_status()
	{
		return tfr_status;
	}
	public void setTfr_status(String tfr_status)
	{
		this.tfr_status = tfr_status;
	}
	
	private String sector_expiry_date;
	@Column(name = "sector_expiry_date")
	public String getSector_expiry_date()
	{
		return sector_expiry_date;
	}
	public void setSector_expiry_date(String sector_expiry_date)
	{
		this.sector_expiry_date = sector_expiry_date;
	}

	private String upload_voucher;
	public String getUpload_voucher()
	{
		return upload_voucher;
	}
	public void setUpload_voucher(String upload_voucher)
	{
		this.upload_voucher = upload_voucher;
	}
	
	private String upload_auth_letter;
	public String getUpload_auth_letter()
	{
		return upload_auth_letter;
	}
	public void setUpload_auth_letter(String upload_auth_letter)
	{
		this.upload_auth_letter = upload_auth_letter;
	}

}
