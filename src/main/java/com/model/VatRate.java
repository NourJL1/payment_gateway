package com.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "VAT_RATE")
public class VatRate  {
	@Id
    @Column(name = "VAT_CODE", length = 50)
	@JsonProperty("vatCode")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer vatCode;

	@Column(name = "VAT_RATE", nullable = false, precision = 5, scale = 4)
    private BigDecimal vatRate; // Changed from Integer to BigDecimal

    @Column(name = "VAT_LABE", length = 100)
    private String vatLabe;
    
    @Column(name = "VAT_IDEN", length = 100)
    private String vatIden;

    @Column(name = "VAT_ACTIVE", nullable = false)
    private Integer vatActive = 1; // Default to active

    // Getters and Setters
   

    

	

	public String getVatLabe() {
        return vatLabe;
    }

    

	public void setVatLabe(String vatLabe) {
        this.vatLabe = vatLabe;
    }

    public Integer getVatActive() {
        return vatActive;
    }

    public void setVatActive(Integer vatActive) {
        this.vatActive = vatActive;
    }

	public Integer getVatCode() {
		return vatCode;
	}

	public void setVatCode(Integer vatCode) {
		this.vatCode = vatCode;
	}

	

	

	public BigDecimal getVatRate() {
		return vatRate;
	}

	public void setVatRate(BigDecimal vatRate) {
		this.vatRate = vatRate;
	}

	public String getVatIden() {
		return vatIden;
	}

	public void setVatIden(String vatIden) {
		this.vatIden = vatIden;
	}

	public VatRate(Integer vatCode, BigDecimal vatRate, String vatLabe, String vatIden, Integer vatActive) {
		super();
		this.vatCode = vatCode;
		this.vatRate = vatRate;
		this.vatLabe = vatLabe;
		this.vatIden = vatIden;
		this.vatActive = vatActive;
	}



	public VatRate() {}

	
    
}
