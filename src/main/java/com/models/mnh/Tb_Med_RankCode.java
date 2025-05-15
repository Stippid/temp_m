package com.models.mnh;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tb_med_rankcode", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class Tb_Med_RankCode {
	
	private int id;
	
	private String rank_code;
	private String rank_desc;
	private Date created_on;
	private String created_by;
	private Date modified_on;
	private String modified_by;
	private String category_code;
	
	private String service;
	private int order_index;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
  	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(length =10)
	public String getRank_code() {
		return rank_code;
	}
	public void setRank_code(String rank_code) {
		this.rank_code = rank_code;
	}
	@Column(length =100)
	public String getRank_desc() {
		return rank_desc;
	}
	public void setRank_desc(String rank_desc) {
		this.rank_desc = rank_desc;
	}
	public Date getCreated_on() {
		return created_on;
	}
	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}
	@Column(length =35)
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
	@Column(length =35)
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	@Column(length =10)
	public String getCategory_code() {
		return category_code;
	}
	public void setCategory_code(String category_code) {
		this.category_code = category_code;
	}
	@Column(length =10)
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public int getOrder_index() {
		return order_index;
	}
	public void setOrder_index(int order_index) {
		this.order_index = order_index;
	}
	
	private Set<Tb_Med_Daily_Disease_Surv_Details> rnk = new HashSet<Tb_Med_Daily_Disease_Surv_Details>(0);
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rank")
	public Set<Tb_Med_Daily_Disease_Surv_Details> getRnk() {
		return rnk;
	}
	public void setRnk(Set<Tb_Med_Daily_Disease_Surv_Details> rnk) {
		this.rnk = rnk;
	}
	
	
    private Set<Tb_Med_Daily_Hosp_Adm_Off_Vip> viprank = new HashSet<Tb_Med_Daily_Hosp_Adm_Off_Vip>(0);
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "v_rank")
	public Set<Tb_Med_Daily_Hosp_Adm_Off_Vip> getViprank() {
		return viprank;
	}
	public void setViprank(Set<Tb_Med_Daily_Hosp_Adm_Off_Vip> viprank) {
		this.viprank = viprank;
	}
	
	
    private Set<Tb_Med_Daily_Hosp_Adm_Amc> admrank = new HashSet<Tb_Med_Daily_Hosp_Adm_Amc>(0);
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "a_rank")
	public Set<Tb_Med_Daily_Hosp_Adm_Amc> getAdmrank() {
		return admrank;
	}
	public void setAdmrank(Set<Tb_Med_Daily_Hosp_Adm_Amc> admrank) {
		this.admrank = admrank;
	}
	
	
	 private Set<Tb_Med_Unusual_Occurrence> un_rank = new HashSet<Tb_Med_Unusual_Occurrence>(0);
		@OneToMany(fetch = FetchType.LAZY, mappedBy = "uo_rank")
		public Set<Tb_Med_Unusual_Occurrence> getUn_rank() {
			return un_rank;
		}
		public void setUn_rank(Set<Tb_Med_Unusual_Occurrence> un_rank) {
			this.un_rank = un_rank;
		}
	
		private Set<Tb_Med_HIV> hiv_rank = new HashSet<Tb_Med_HIV>(0);
		@OneToMany(fetch = FetchType.LAZY, mappedBy = "hivaid_rank")
		public Set<Tb_Med_HIV> getHiv_rank() {
			return hiv_rank;
		}
		public void setHiv_rank(Set<Tb_Med_HIV> hiv_rank) {
			this.hiv_rank = hiv_rank;
		}
	
		
		
		private Set<Tb_Med_Imb> imb_rank = new HashSet<Tb_Med_Imb>(0);
		@OneToMany(fetch = FetchType.LAZY, mappedBy = "invalid_rank")
		public Set<Tb_Med_Imb> getImb_rank() {
			return imb_rank;
		}
		public void setImb_rank(Set<Tb_Med_Imb> imb_rank) {
			this.imb_rank = imb_rank;
		}
	
		private Set<Tb_Med_Death> morta_rank = new HashSet<Tb_Med_Death>(0);
		@OneToMany(fetch = FetchType.LAZY, mappedBy = "death_rank")
		public Set<Tb_Med_Death> getMorta_rank() {
			return morta_rank;
		}
		public void setMorta_rank(Set<Tb_Med_Death> morta_rank) {
			this.morta_rank = morta_rank;
		}
		
		private Set<Tb_Med_Eir> eir_rnk = new HashSet<Tb_Med_Eir>(0);
		@OneToMany(fetch = FetchType.LAZY, mappedBy = "eir_rank")
		public Set<Tb_Med_Eir> getEir_rnk() {
			return eir_rnk;
		}
		public void setEir_rnk(Set<Tb_Med_Eir> eir_rnk) {
			this.eir_rnk = eir_rnk;
		}
		
	
}