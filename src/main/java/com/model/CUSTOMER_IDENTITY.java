package com.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "CUSTOMER_IDENTITY", uniqueConstraints = {@UniqueConstraint(columnNames = "CID_NUM")})
// @Data

public class CUSTOMER_IDENTITY {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CID_CODE", nullable = false, unique = true)
	private Integer cidCode;

	@Column(name = "CID_NUM", nullable = false)
	private String cidNum;

	/*
	 * @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	 * 
	 * @JoinColumn(name = "CID_CUS_CODE", nullable = false)
	 * 
	 * @NotNull(message = "Customer is required")
	 * private CUSTOMER customer;
	 */

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "CID_CIT_CODE", nullable = false)
	@NotNull(message = "Customer Identity Type is required")
	private CUSTOMER_IDENTITY_TYPE customerIdentityType;

	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "CID_CDL_CODE", nullable = false)
	@NotNull(message = "Customer Document List is required")
	private CUSTOMER_DOC_LISTE customerDocListe;

	@PrePersist
	public void onCreate() {
		this.customerDocListe.setCdlLabe(this.customerIdentityType.getCitIden() + "-"
				+ String.format("%06d", this.customerDocListe.getCdlCode()));
	}

	public boolean isEmpty() {
		return this.cidNum == null || this.cidNum.isEmpty();
	}

	public Integer getCidCode() {
		return cidCode;
	}

	public void setCidCode(Integer cidCode) {
		this.cidCode = cidCode;
	}

	public String getCidNum() {
		return cidNum;
	}

	public void setCidNum(String cidNum) {
		this.cidNum = cidNum;
	}

	public CUSTOMER_IDENTITY_TYPE getCustomerIdentityType() {
		return customerIdentityType;
	}

	public void setCustomerIdentityType(CUSTOMER_IDENTITY_TYPE customerIdentityType) {
		this.customerIdentityType = customerIdentityType;
	}

	public CUSTOMER_DOC_LISTE getCustomerDocListe() {
		return customerDocListe;
	}

	public void setCustomerDocListe(CUSTOMER_DOC_LISTE customerDocListe) {
		this.customerDocListe = customerDocListe;
	}

	public CUSTOMER_IDENTITY(Integer cidCode, String cidNum,
			@NotNull(message = "Customer Identity Type is required") CUSTOMER_IDENTITY_TYPE customerIdentityType,
			@NotNull(message = "Customer Document List is required") CUSTOMER_DOC_LISTE customerDocListe) {
		super();
		this.cidCode = cidCode;
		this.cidNum = cidNum;
		this.customerIdentityType = customerIdentityType;
		this.customerDocListe = customerDocListe;
	}

	public CUSTOMER_IDENTITY() {
	}

}
