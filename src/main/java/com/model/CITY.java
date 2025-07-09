package com.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "CITY")
@Data

public class CITY {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CTY_CODE", nullable = false, unique = true)
    private Integer ctyCode;

    @Column(name = "CTY_IDEN", nullable = false)
    private Integer ctyIden;

    @Column(name = "CTY_LABE", nullable = false)
    private String ctyLabe;

    
    @ManyToOne(cascade = CascadeType.PERSIST)
    //@JsonIgnore
    @JoinColumn(name = "CTY_CTR_CODE", referencedColumnName = "CTR_CODE", nullable = false)
    private COUNTRY country;
    
    
    

	public CITY(Integer ctyCode, Integer ctyIden, String ctyLabe, COUNTRY country) {
		super();
		this.ctyCode = ctyCode;
		this.ctyIden = ctyIden;
		this.ctyLabe = ctyLabe;
		this.country = country;
	}



	public Integer getCtyCode() {
		return ctyCode;
	}



	public void setCtyCode(Integer ctyCode) {
		this.ctyCode = ctyCode;
	}



	public Integer getCtyIden() {
		return ctyIden;
	}

	public void setCtyIden(Integer ctyIden) {
		this.ctyIden = ctyIden;
	}

	public String getCtyLabe() {
		return ctyLabe;
	}

	public void setCtyLabe(String ctyLabe) {
		this.ctyLabe = ctyLabe;
	}

	public COUNTRY getCountry() {
		return country;
	}

	public void setCountry(COUNTRY country) {
		this.country = country;
	}
	// Constructeur par défaut requis par Hibernate
    public CITY() {
    }
    
}
