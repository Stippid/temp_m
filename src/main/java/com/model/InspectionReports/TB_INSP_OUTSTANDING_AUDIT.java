package com.model.InspectionReports;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_insp_outstanding_audit", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
public class TB_INSP_OUTSTANDING_AUDIT {

	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
	  private String broughtPreviousObj;
	    private String broughtPreviousObserv;
	    private String broughtPreviousRemark;

	    private String raisedReportObj;
	    private String raisedReportObserv;
	    private String raisedReportRemark;

	    private String settledDuringObj;
	    private String settledDuringObserv;
	    private String settledDuringRemark;

	    private String remainingObj;
	    private String remainingObserv;
	    private String remainingRemark;

	    private String difficultiesObj3;
	    private String difficultiesObj1;
	    private String difficultiesObserv3;
	    private String difficultiesObserv1;
	    private String difficultiesRemark3;
	    private String difficultiesRemark1;

	    private String created_by;
		private Date created_date;
		private String modified_by;
		private Date modified_on;
		private String sus_no;
		private Integer status;
		private String year;
		
		
		
		
		
	    public String getCreated_by() {
			return created_by;
		}
		public void setCreated_by(String created_by) {
			this.created_by = created_by;
		}
	
		public String getModified_by() {
			return modified_by;
		}
		public void setModified_by(String modified_by) {
			this.modified_by = modified_by;
		}
		public Date getModified_on() {
			return modified_on;
		}
		public void setModified_on(Date modified_on) {
			this.modified_on = modified_on;
		}
		public String getSus_no() {
			return sus_no;
		}
		public void setSus_no(String sus_no) {
			this.sus_no = sus_no;
		}
		public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBroughtPreviousObj() {
		return broughtPreviousObj;
	}
	public void setBroughtPreviousObj(String broughtPreviousObj) {
		this.broughtPreviousObj = broughtPreviousObj;
	}
	public String getBroughtPreviousObserv() {
		return broughtPreviousObserv;
	}
	public void setBroughtPreviousObserv(String broughtPreviousObserv) {
		this.broughtPreviousObserv = broughtPreviousObserv;
	}
	public String getBroughtPreviousRemark() {
		return broughtPreviousRemark;
	}
	public void setBroughtPreviousRemark(String broughtPreviousRemark) {
		this.broughtPreviousRemark = broughtPreviousRemark;
	}
	public String getRaisedReportObj() {
		return raisedReportObj;
	}
	public void setRaisedReportObj(String raisedReportObj) {
		this.raisedReportObj = raisedReportObj;
	}
	public String getRaisedReportObserv() {
		return raisedReportObserv;
	}
	public void setRaisedReportObserv(String raisedReportObserv) {
		this.raisedReportObserv = raisedReportObserv;
	}
	public String getRaisedReportRemark() {
		return raisedReportRemark;
	}
	public void setRaisedReportRemark(String raisedReportRemark) {
		this.raisedReportRemark = raisedReportRemark;
	}
	public String getSettledDuringObj() {
		return settledDuringObj;
	}
	public void setSettledDuringObj(String settledDuringObj) {
		this.settledDuringObj = settledDuringObj;
	}
	public String getSettledDuringObserv() {
		return settledDuringObserv;
	}
	public void setSettledDuringObserv(String settledDuringObserv) {
		this.settledDuringObserv = settledDuringObserv;
	}
	public String getSettledDuringRemark() {
		return settledDuringRemark;
	}
	public void setSettledDuringRemark(String settledDuringRemark) {
		this.settledDuringRemark = settledDuringRemark;
	}
	public String getRemainingObj() {
		return remainingObj;
	}
	public void setRemainingObj(String remainingObj) {
		this.remainingObj = remainingObj;
	}
	public String getRemainingObserv() {
		return remainingObserv;
	}
	public void setRemainingObserv(String remainingObserv) {
		this.remainingObserv = remainingObserv;
	}
	public String getRemainingRemark() {
		return remainingRemark;
	}
	public void setRemainingRemark(String remainingRemark) {
		this.remainingRemark = remainingRemark;
	}
	public String getDifficultiesObj3() {
		return difficultiesObj3;
	}
	public void setDifficultiesObj3(String difficultiesObj3) {
		this.difficultiesObj3 = difficultiesObj3;
	}
	public String getDifficultiesObj1() {
		return difficultiesObj1;
	}
	public void setDifficultiesObj1(String difficultiesObj1) {
		this.difficultiesObj1 = difficultiesObj1;
	}
	public String getDifficultiesObserv3() {
		return difficultiesObserv3;
	}
	public void setDifficultiesObserv3(String difficultiesObserv3) {
		this.difficultiesObserv3 = difficultiesObserv3;
	}
	public String getDifficultiesObserv1() {
		return difficultiesObserv1;
	}
	public void setDifficultiesObserv1(String difficultiesObserv1) {
		this.difficultiesObserv1 = difficultiesObserv1;
	}
	public String getDifficultiesRemark3() {
		return difficultiesRemark3;
	}
	public void setDifficultiesRemark3(String difficultiesRemark3) {
		this.difficultiesRemark3 = difficultiesRemark3;
	}
	public String getDifficultiesRemark1() {
		return difficultiesRemark1;
	}
	public void setDifficultiesRemark1(String difficultiesRemark1) {
		this.difficultiesRemark1 = difficultiesRemark1;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
		

}
