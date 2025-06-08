package com.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private Integer ctrIden;
    
    
    @Column(name = "CTR_LABE", nullable = false)
    private String ctrLabe;  // Change type to String
    
    
    @OneToMany(mappedBy = "country", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<CITY> cities;

    
    

    public Integer getCtrCode() {
		return ctrCode;
	}



	public void setCtrCode(Integer ctrCode) {
		this.ctrCode = ctrCode;
	}



	public Integer getCtrIden() {
		return ctrIden;
	}



	public void setCtrIden(Integer ctrIden) {
		this.ctrIden = ctrIden;
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



	public COUNTRY(Integer ctrIden, String ctrLabe, List<CITY> cities) {
        this.ctrIden = ctrIden;
        this.ctrLabe = ctrLabe;
        this.cities = cities;
    }
	
	public COUNTRY() {}
}
