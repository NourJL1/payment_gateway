package com.model;

import jakarta.persistence.*;

import lombok.Data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "COUNTRY")
@Data
public class COUNTRY {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CTR_CODE", nullable = false, unique = true)
    private Integer ctrCode;

    @Column(name = "CTR_IDEN", nullable = false)
    private String ctrIden;
    
    
    @Column(name = "CTR_LABE", nullable = false)
    private String ctrLabe;  // Change type to String
    
    
    @OneToMany(mappedBy = "country", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<CITY> cities;

    
    

    public Integer getCtrCode() {
		return ctrCode;
	}



	public void setCtrCode(Integer ctrCode) {
		this.ctrCode = ctrCode;
	}



	public String getCtrIden() {
		return ctrIden;
	}



	public void setCtrIden(String ctrIden) {
		this.ctrIden = ctrIden;
	}

	@PrePersist
	public void setCtrIden() {
		this.ctrIden = "CTR-" + ctrIden;
	}



	public String getCtrLabe() {
		return ctrLabe;
	}



	public void setCtrLabe(String ctrLabe) {
		this.ctrLabe = ctrLabe;
	}



	public List<CITY> getCities() {
		return cities;
	}



	public void setCities(List<CITY> cities) {
		this.cities = cities;
	}



	public COUNTRY(Integer ctrCode, String ctrIden, String ctrLabe, List<CITY> cities) {
		super();
		this.ctrCode = ctrCode;
		this.ctrIden = ctrIden;
		this.ctrLabe = ctrLabe;
		this.cities = cities;
	}
	
	public COUNTRY() {}
}
