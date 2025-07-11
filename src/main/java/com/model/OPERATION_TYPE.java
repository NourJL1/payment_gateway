package com.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "OPERATION_TYPE")
@Data
public class OPERATION_TYPE {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OPT_CODE", nullable = false, unique = true)
	private Integer optCode;

	@Column(name = "OPT_IDEN", nullable = false)
	private String optIden;

	@Column(name = "OPT_LABE", nullable = false)
	private String optLabe;

	@Column(name = "OPT_FSC_IDEN", nullable = false)
	private Integer optFscIden;

	@Column(name = "OPT_FSC_LAB", nullable = false)
	private String optFscLab;

	// Relation ManyToOne avec WALLET
	@ManyToOne
	@JoinColumn(name = "OPT_WAL_CODE", referencedColumnName = "WAL_CODE", nullable = true)
	@JsonIgnore
	private WALLET wallet;

	@OneToMany(mappedBy = "operationType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference("operationType-walletOp")
	private List<WALLET_OPERATION_TYPE_MAP> walletOperationTypeMappings;

	@ManyToOne
	@JoinColumn(name = "OPT_FSC_CODE", referencedColumnName = "FSC_CODE", nullable = false)
	private FEE_SCHEMA feeSchema;

	@OneToMany(mappedBy = "operationType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference("opt-wcotm")
	private List<WALLET_CATEGORY_OPERATION_TYPE_MAP> walletCategoryOperationTypeMappings;

	// Relation ManyToOne avec WALLET_CATEGORY (si applicable)
	@ManyToOne
	@JoinColumn(name = "OPT_WCA_CODE", referencedColumnName = "WCA_CODE", nullable = true)
	private WALLET_CATEGORY walletCategory;

	public Integer getOptCode() {
		return optCode;
	}

	public void setOptCode(Integer optCode) {
		this.optCode = optCode;
	}

	public String getOptIden() {
		return optIden;
	}

	public void setOptIden(String optIden) {
		this.optIden = optIden;
	}

	@PrePersist
	public void setOptIden() {
		this.optIden = "OPT-" + optLabe.substring(0, 3).toUpperCase();
	}

	public String getOptLabe() {
		return optLabe;
	}

	public void setOptLabe(String optLabe) {
		this.optLabe = optLabe;
	}

	public Integer getOptFscIden() {
		return optFscIden;
	}

	public void setOptFscIden(Integer optFscIden) {
		this.optFscIden = optFscIden;
	}

	public String getOptFscLab() {
		return optFscLab;
	}

	public void setOptFscLab(String optFscLab) {
		this.optFscLab = optFscLab;
	}

	public WALLET getWallet() {
		return wallet;
	}

	public void setWallet(WALLET wallet) {
		this.wallet = wallet;
	}

	public List<WALLET_OPERATION_TYPE_MAP> getWalletOperationTypeMappings() {
		return walletOperationTypeMappings;
	}

	public void setWalletOperationTypeMappings(List<WALLET_OPERATION_TYPE_MAP> walletOperationTypeMappings) {
		this.walletOperationTypeMappings = walletOperationTypeMappings;
	}

	public FEE_SCHEMA getFeeSchema() {
		return feeSchema;
	}

	public void setFeeSchema(FEE_SCHEMA feeSchema) {
		this.feeSchema = feeSchema;
	}

	public List<WALLET_CATEGORY_OPERATION_TYPE_MAP> getWalletCategoryOperationTypeMappings() {
		return walletCategoryOperationTypeMappings;
	}

	public void setWalletCategoryOperationTypeMappings(
			List<WALLET_CATEGORY_OPERATION_TYPE_MAP> walletCategoryOperationTypeMappings) {
		this.walletCategoryOperationTypeMappings = walletCategoryOperationTypeMappings;
	}

	public WALLET_CATEGORY getWalletCategory() {
		return walletCategory;
	}

	public void setWalletCategory(WALLET_CATEGORY walletCategory) {
		this.walletCategory = walletCategory;
	}

	public OPERATION_TYPE(Integer optCode, String optIden, String optLabe, Integer optFscIden, String optFscLab,
			WALLET wallet, List<WALLET_OPERATION_TYPE_MAP> walletOperationTypeMappings, FEE_SCHEMA feeSchema,
			List<WALLET_CATEGORY_OPERATION_TYPE_MAP> walletCategoryOperationTypeMappings,
			WALLET_CATEGORY walletCategory) {
		super();
		this.optCode = optCode;
		this.optIden = optIden;
		this.optLabe = optLabe;
		this.optFscIden = optFscIden;
		this.optFscLab = optFscLab;
		this.wallet = wallet;
		this.walletOperationTypeMappings = walletOperationTypeMappings;
		this.feeSchema = feeSchema;
		this.walletCategoryOperationTypeMappings = walletCategoryOperationTypeMappings;
		this.walletCategory = walletCategory;
	}

	public OPERATION_TYPE() {
	}

}
