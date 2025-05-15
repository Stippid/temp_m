package com.models;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "tb_tms_emar_report_main" , uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class TB_TMS_EMAR_REPORT_MAIN {
	
	private int id;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
  	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	private String sus_no;
	@Column(name = "sus_no")
	public String getSus_no()
	{
		return sus_no;
	}
	public void setSus_no(String sus_no)
	{
		this.sus_no = sus_no;
	}

	private Date date_of_mvcr;
	@Column(name = "date_of_mvcr")
	public Date getDate_of_mvcr()
	{
		return date_of_mvcr;
	}
	public void setDate_of_mvcr(Date date_of_mvcr)
	{
		this.date_of_mvcr = date_of_mvcr;
	}

	private Date dt_of_submsn;
	@Column(name = "dt_of_submsn")
	public Date getDt_of_submsn()
	{
		return dt_of_submsn;
	}
	public void setDt_of_submsn(Date dt_of_submsn)
	{
		this.dt_of_submsn = dt_of_submsn;
	}

	private String status;
	@Column(name = "status")
	public String getStatus()
	{
		return status;
	}
	public void setStatus(String status)
	{
		this.status = status;
	}

	private String approved_by;
	@Column(name = "approved_by")
	public String getApproved_by()
	{
		return approved_by;
	}
	public void setApproved_by(String approved_by)
	{
		this.approved_by = approved_by;
	}

	private Date approve_date;
	@Column(name = "approve_date")
	public Date getApprove_date()
	{
		return approve_date;
	}
	public void setApprove_date(Date approve_date)
	{
		this.approve_date = approve_date;
	}

	private String creation_by;
	@Column(name = "creation_by")
	public String getCreation_by()
	{
		return creation_by;
	}
	public void setCreation_by(String creation_by)
	{
		this.creation_by = creation_by;
	}

	private Date creation_date;
	@Column(name = "creation_date")
	public Date getCreation_date()
	{
		return creation_date;
	}
	public void setCreation_date(Date creation_date)
	{
		this.creation_date = creation_date;
	}

	private String modify_by;
	@Column(name = "modify_by")
	public String getModify_by()
	{
		return modify_by;
	}
	public void setModify_by(String modify_by)
	{
		this.modify_by = modify_by;
	}

	private Date modify_date;
	@Column(name = "modify_date")
	public Date getModify_date()
	{
		return modify_date;
	}
	public void setModify_date(Date modify_date)
	{
		this.modify_date = modify_date;
	}

	private String deleted_by;
	@Column(name = "deleted_by")
	public String getDeleted_by()
	{
		return deleted_by;
	}
	public void setDeleted_by(String deleted_by)
	{
		this.deleted_by = deleted_by;
	}

	private Date deleted_date;
	@Column(name = "deleted_date")
	public Date getDeleted_date()
	{
		return deleted_date;
	}
	public void setDeleted_date(Date deleted_date)
	{
		this.deleted_date = deleted_date;
	}

	private int version_no;
	@Column(name = "version_no")
	public int getVersion_no()
	{
		return version_no;
	}
	public void setVersion_no(int version_no)
	{
		this.version_no = version_no;
	}

	private String softdelete;
	@Column(name = "softdelete")
	public String getSoftdelete()
	{
		return softdelete;
	}
	public void setSoftdelete(String softdelete)
	{
		this.softdelete = softdelete;
	}

	private String filler_1;
	@Column(name = "filler_1")
	public String getFiller_1()
	{
		return filler_1;
	}
	public void setFiller_1(String filler_1)
	{
		this.filler_1 = filler_1;
	}

	private String filler_2;
	@Column(name = "filler_2")
	public String getFiller_2()
	{
		return filler_2;
	}
	public void setFiller_2(String filler_2)
	{
		this.filler_2 = filler_2;
	}

	private String filler_3;
	@Column(name = "filler_3")
	public String getFiller_3()
	{
		return filler_3;
	}
	public void setFiller_3(String filler_3)
	{
		this.filler_3 = filler_3;
	}

	private String app_rej_remarks;
	@Column(name = "app_rej_remarks")
	public String getApp_rej_remarks()
	{
		return app_rej_remarks;
	}
	public void setApp_rej_remarks(String app_rej_remarks)
	{
		this.app_rej_remarks = app_rej_remarks;
	}

 
}