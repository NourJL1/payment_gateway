package com.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "FEE_SCHEMA")
@Data

public class FEE_SCHEMA {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FSC_CODE", nullable = false, unique = true)
	private Integer fscCode;

	@Column(name = "FSC_IDEN", nullable = false)
	private String fscIden;

	@Column(name = "FSC_LABE", nullable = false)
	private String fscLabe;

	@OneToMany(mappedBy = "feeSchema", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore

	private List<FEE_RULE> feeRules;

	// Default constructor (required by JPA)
	public FEE_SCHEMA() {
	}

	public Integer getFscCode() {
		return fscCode;
	}

	public void setFscCode(Integer fscCode) {
		this.fscCode = fscCode;
	}

	public String getFscIden() {
		return fscIden;
	}

	public void setFscIden(String fscIden) {
		this.fscIden = fscIden;
	}

	@PrePersist
	public void setFscIden() {
		this.fscIden = "FSC-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss")) + "-"
				+ UUID.randomUUID().toString().substring(0, 4).toUpperCase();
	}

	public String getFscLabe() {
		return fscLabe;
	}

	public void setFscLabe(String fscLabe) {
		this.fscLabe = fscLabe;
	}

	public List<FEE_RULE> getFeeRules() {
		return feeRules;
	}

	public void setFeeRules(List<FEE_RULE> feeRules) {
		this.feeRules = feeRules;
	}

	public FEE_SCHEMA(Integer fscCode, String fscIden, String fscLabe, List<FEE_RULE> feeRules) {
		super();
		this.fscCode = fscCode;
		this.fscIden = fscIden;
		this.fscLabe = fscLabe;
		this.feeRules = feeRules;
	}

}
