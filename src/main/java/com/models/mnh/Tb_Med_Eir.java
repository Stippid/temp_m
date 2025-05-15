package com.models.mnh;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.FetchType;



@Entity
@Table(name = "tb_med_eir", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})

public class Tb_Med_Eir {
	
		private int id;
		private String sus_no;
		private int disease;
		private String case_no;
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private Date datee;
		private String persnl_no;
		private String personal_name; 
		private String service;
		private String relationship;
		private String category;
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private Date date_of_birth;
		private BigInteger adhar_card_no;
		private String age;
		private String personnel_unit;
		private String present_address;
		private BigInteger contact_no;
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private Date date_onset_symp;
		private String type_of_symptom;
		@DateTimeFormat(pattern ="yyyy-MM-dd")
		private Date date_repo_med_centre;
		private String history;
		private String prob_source_insp;
		private String en_loc1;
		private String en_loc2;
		private String en_loc3;
		private Integer breeding_found;
		private Integer follow_up_down;
		private String final_epid_diag;
		private String final_epid_diagnosis;
		private String action_for_patient;
		private String action_by_sho;
		private String recommendation_unit;
		private String recommendation_indl;
		private String repo_type;	
		private String source_of_water;
		private Integer history_Of_past;
		private String other_action_by_sho;
		private String created_by;
		@DateTimeFormat(pattern = "dd/MM/yyyy")
		private Date created_on;
	   private String modified_by;
		@DateTimeFormat(pattern = "dd/MM/yyyy")
		private Date modified_on;
		
		@Id
		@GeneratedValue(strategy = IDENTITY)
		@Column(name = "id", unique = true, nullable = false)
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
	
		
		public int getDisease() {
			return disease;
		}
		public void setDisease(int disease) {
			this.disease = disease;
		}
		@Column(length = 50)
		public String getCase_no() {
			return case_no;
		}
		public void setCase_no(String case_no) {
			this.case_no = case_no;
		}
	
		public Date getDatee() {
			return datee;
		}
		public void setDatee(Date datee) {
			this.datee = datee;
		}
		@Column(length = 10)
		public String getRelationship() {
			return relationship;
		}
		public void setRelationship(String relationship) {
			this.relationship = relationship;
		}
		@Column(length = 20)
		public String getAge() {
			return age;
		}
		public void setAge(String age) {
			this.age = age;
		}
		public BigInteger getAdhar_card_no() {
			return adhar_card_no;
		}
		public void setAdhar_card_no(BigInteger adhar_card_no) {
			this.adhar_card_no = adhar_card_no;
		}
		@Column(length = 250)
		public String getPersonnel_unit() {
			return personnel_unit;
		}
		public void setPersonnel_unit(String personnel_unit) {
			this.personnel_unit = personnel_unit;
		}
		@Column(length = 200)
		public String getPresent_address() {
			return present_address;
		}
		public void setPresent_address(String present_address) {
			this.present_address = present_address;
		}
		public BigInteger getContact_no() {
			return contact_no;
		}
		public void setContact_no(BigInteger contact_no) {
			this.contact_no = contact_no;
		}
		@Column(length = 10)
		public String getType_of_symptom() {
			return type_of_symptom;
		}
		public void setType_of_symptom(String type_of_symptom) {
			this.type_of_symptom = type_of_symptom;
		}
		public Date getDate_onset_symp() {
			return date_onset_symp;
		}
		public void setDate_onset_symp(Date date_onset_symp) {
			this.date_onset_symp = date_onset_symp;
		}
		public Date getDate_repo_med_centre() {
			return date_repo_med_centre;
		}
		public void setDate_repo_med_centre(Date date_repo_med_centre) {
			this.date_repo_med_centre = date_repo_med_centre;
		}
		@Column(length = 250)
		public String getHistory() {
			return history;
		}
		public void setHistory(String history) {
			this.history = history;
		}
		
		@Column(length = 250)
		public String getProb_source_insp() {
			return prob_source_insp;
		}
		public void setProb_source_insp(String prob_source_insp) {
			this.prob_source_insp = prob_source_insp;
		}
		@Column(length = 100)
		public String getFinal_epid_diag() {
			return final_epid_diag;
		}
		public void setFinal_epid_diag(String final_epid_diag) {
			this.final_epid_diag = final_epid_diag;
		}
		@Column(length = 250)
		public String getFinal_epid_diagnosis() {
			return final_epid_diagnosis;
		}
		public void setFinal_epid_diagnosis(String final_epid_diagnosis) {
			this.final_epid_diagnosis = final_epid_diagnosis;
		}
		@Column(length = 250)
		public String getAction_for_patient() {
			return action_for_patient;
		}
		public void setAction_for_patient(String action_for_patient) {
			this.action_for_patient = action_for_patient;
		}
		@Column(length = 250)
		public String getAction_by_sho() {
			return action_by_sho;
		}
		public void setAction_by_sho(String action_by_sho) {
			this.action_by_sho = action_by_sho;
		}
		@Column(length = 250)
		public String getRecommendation_unit() {
			return recommendation_unit;
		}
		public void setRecommendation_unit(String recommendation_unit) {
			this.recommendation_unit = recommendation_unit;
		}
		@Column(length = 35)
		public String getCreated_by() {
			return created_by;
		}
		public void setCreated_by(String created_by) {
			this.created_by = created_by;
		}
		public Date getCreated_on() {
			return created_on;
		}
		public void setCreated_on(Date created_on) {
			this.created_on = created_on;
		}
		@Column(length = 20)
		public String getPersnl_no() {
			return persnl_no;
		}
		public void setPersnl_no(String persnl_no) {
			this.persnl_no = persnl_no;
		}
		@Column(length = 100)
		public String getEn_loc1() {
			return en_loc1;
		}
		public void setEn_loc1(String en_loc1) {
			this.en_loc1 = en_loc1;
		}
		@Column(length = 100)
		public String getEn_loc2() {
			return en_loc2;
		}
		public void setEn_loc2(String en_loc2) {
			this.en_loc2 = en_loc2;
		}
		@Column(length = 100)
		public String getEn_loc3() {
			return en_loc3;
		}
		public void setEn_loc3(String en_loc3) {
			this.en_loc3 = en_loc3;
		}
		@Column(length = 20)
		public String getRepo_type() {
			return repo_type;
		}
		public void setRepo_type(String repo_type) {
			this.repo_type = repo_type;
		}
		@Column(length = 8)
		public String getSus_no() {
			return sus_no;
		}
		public void setSus_no(String sus_no) {
			this.sus_no = sus_no;
		}
		
		@Column(length = 10)
		public String getService() {
			return service;
		}
		public void setService(String service) {
			this.service = service;
		}
		
	
		@Column(length = 10)
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
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
		public Date getDate_of_birth() {
			return date_of_birth;
		}
		public void setDate_of_birth(Date date_of_birth) {
			this.date_of_birth = date_of_birth;
		}
		
		


		public String getPersonal_name() {
			return personal_name;
		}
		public void setPersonal_name(String personal_name) {
			this.personal_name = personal_name;
		}


		private Tb_Med_RankCode eir_rank;
		   @ManyToOne(fetch = FetchType.LAZY)
			@JoinColumn(name = "rank", nullable = false)
			public Tb_Med_RankCode getEir_rank() {
				return eir_rank;
			}
			public void setEir_rank(Tb_Med_RankCode eir_rank) {
				this.eir_rank = eir_rank;
			}
			public Integer getFollow_up_down() {
				return follow_up_down;
			}
			public void setFollow_up_down(Integer follow_up_down) {
				this.follow_up_down = follow_up_down;
			}
			public Integer getBreeding_found() {
				return breeding_found;
			}
			public void setBreeding_found(Integer breeding_found) {
				this.breeding_found = breeding_found;
			}
			public String getRecommendation_indl() {
				return recommendation_indl;
			}
			public void setRecommendation_indl(String recommendation_indl) {
				this.recommendation_indl = recommendation_indl;
			}
			
			public Integer getHistory_Of_past() {
				return history_Of_past;
			}
			public void setHistory_Of_past(Integer history_Of_past) {
				this.history_Of_past = history_Of_past;
			}
			public String getOther_action_by_sho() {
				return other_action_by_sho;
			}
			public void setOther_action_by_sho(String other_action_by_sho) {
				this.other_action_by_sho = other_action_by_sho;
			}
			public String getSource_of_water() {
				return source_of_water;
			}
			public void setSource_of_water(String source_of_water) {
				this.source_of_water = source_of_water;
			}
		
}
