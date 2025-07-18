package com.model;

import java.util.UUID;

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
	private String ctyIden;

	@Column(name = "CTY_LABE", nullable = false)
	private String ctyLabe;

	@ManyToOne(cascade = CascadeType.PERSIST)
	// @JsonIgnore
	@JoinColumn(name = "CTY_CTR_CODE", referencedColumnName = "CTR_CODE", nullable = false)
	private COUNTRY country;

	public CITY(Integer ctyCode, String ctyIden, String ctyLabe, COUNTRY country) {
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

	public String getCtyIden() {
		return ctyIden;
	}

	public void setCtyIden(String ctyIden) {
		this.ctyIden = ctyIden;
	}

	@PrePersist
	public void setCtyIden() {
		this.ctyIden = "CTY-" + country.getCtrIden().substring(4, 6) + "-"
				+ UUID.randomUUID().toString().substring(0, 4).toUpperCase();
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
