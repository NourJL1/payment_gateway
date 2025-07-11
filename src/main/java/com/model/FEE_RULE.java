package com.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "FEE_RULE")
@Data

public class FEE_RULE {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FRU_CODE", nullable = false, unique = true)
	private Integer fruCode;

	@Column(name = "FRU_IDEN", nullable = false)
	private String fruIden;

	@Column(name = "FRU_LABE", nullable = false)
	private String fruLabe;

	@Column(name = "FRU_PRIMARY_WALLET_ID", nullable = false)
	private String fruPrimaryWalletId;

	@Column(name = "FRU_PRIMARY_AMOUNT", nullable = false)
	private Float fruPrimaryAmount;

	@Column(name = "FRU_PRIMARY_FEES_ID", nullable = false)
	private Integer fruPrimaryFeesId;

	@Column(name = "FRU_FEES_WALLET_ID", nullable = false)
	private Integer fruFeesWalletId;

	@Column(name = "FRU_FEES_AMOUNT", nullable = false)
	private Float fruFeesAmount;

	@ManyToOne
	@JoinColumn(name = "FRU_TVA_CODE", referencedColumnName = "VAT_CODE")
	private VatRate fruTva;

	@Column(name = "FRU_TVA_WALLET_ID", nullable = false)
	private Integer fruTvaWalletId;

	@Column(name = "FRU_TVA_AMOUNT", nullable = false)
	private Float fruTvaAmount;

	@Column(name = "FRU_SENS", nullable = false)
	private String fruSens;

	@ManyToOne
	@JoinColumn(name = "FRU_FRT_CODE", referencedColumnName = "FRT_CODE", nullable = false)
	private FEE_RULE_TYPE feeRuleType;

	@ManyToOne
	@JoinColumn(name = "FRU_FSC_CODE", referencedColumnName = "FSC_CODE", nullable = false)
	private FEE_SCHEMA feeSchema;

	public Integer getFruCode() {
		return fruCode;
	}

	public void setFruCode(Integer fruCode) {
		this.fruCode = fruCode;
	}

	public String getFruIden() {
		return fruIden;
	}

	public void setFruIden(String fruIden) {
		this.fruIden = fruIden;
	}

	@PrePersist
	public void setFruIden() {
		this.fruIden = "FRU-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss")) + "-"
				+ UUID.randomUUID().toString().substring(0, 4).toUpperCase();
	}

	public String getFruLabe() {
		return fruLabe;
	}

	public void setFruLabe(String fruLabe) {
		this.fruLabe = fruLabe;
	}

	public String getFruPrimaryWalletId() {
		return fruPrimaryWalletId;
	}

	public void setFruPrimaryWalletId(String fruPrimaryWalletId) {
		this.fruPrimaryWalletId = fruPrimaryWalletId;
	}

	public Float getFruPrimaryAmount() {
		return fruPrimaryAmount;
	}

	public void setFruPrimaryAmount(Float fruPrimaryAmount) {
		this.fruPrimaryAmount = fruPrimaryAmount;
	}

	public Integer getFruPrimaryFeesId() {
		return fruPrimaryFeesId;
	}

	public void setFruPrimaryFeesId(Integer fruPrimaryFeesId) {
		this.fruPrimaryFeesId = fruPrimaryFeesId;
	}

	public Integer getFruFeesWalletId() {
		return fruFeesWalletId;
	}

	public void setFruFeesWalletId(Integer fruFeesWalletId) {
		this.fruFeesWalletId = fruFeesWalletId;
	}

	public Float getFruFeesAmount() {
		return fruFeesAmount;
	}

	public void setFruFeesAmount(Float fruFeesAmount) {
		this.fruFeesAmount = fruFeesAmount;
	}

	public Integer getFruTvaWalletId() {
		return fruTvaWalletId;
	}

	public void setFruTvaWalletId(Integer fruTvaWalletId) {
		this.fruTvaWalletId = fruTvaWalletId;
	}

	public Float getFruTvaAmount() {
		return fruTvaAmount;
	}

	public void setFruTvaAmount(Float fruTvaAmount) {
		this.fruTvaAmount = fruTvaAmount;
	}

	public String getFruSens() {
		return fruSens;
	}

	public void setFruSens(String fruSens) {
		this.fruSens = fruSens;
	}

	public FEE_RULE_TYPE getFeeRuleType() {
		return feeRuleType;
	}

	public void setFeeRuleType(FEE_RULE_TYPE feeRuleType) {
		this.feeRuleType = feeRuleType;
	}

	public FEE_SCHEMA getFeeSchema() {
		return feeSchema;
	}

	public void setFeeSchema(FEE_SCHEMA feeSchema) {
		this.feeSchema = feeSchema;
	}

	public VatRate getFruTva() {
		return fruTva;
	}

	public void setFruTva(VatRate fruTva) {
		this.fruTva = fruTva;
	}

	public FEE_RULE(Integer fruCode, String fruIden, String fruLabe, String fruPrimaryWalletId, Float fruPrimaryAmount,
			Integer fruPrimaryFeesId, Integer fruFeesWalletId, Float fruFeesAmount, VatRate fruTva,
			Integer fruTvaWalletId, Float fruTvaAmount, String fruSens, FEE_RULE_TYPE feeRuleType,
			FEE_SCHEMA feeSchema) {
		super();
		this.fruCode = fruCode;
		this.fruIden = fruIden;
		this.fruLabe = fruLabe;
		this.fruPrimaryWalletId = fruPrimaryWalletId;
		this.fruPrimaryAmount = fruPrimaryAmount;
		this.fruPrimaryFeesId = fruPrimaryFeesId;
		this.fruFeesWalletId = fruFeesWalletId;
		this.fruFeesAmount = fruFeesAmount;
		this.fruTva = fruTva;
		this.fruTvaWalletId = fruTvaWalletId;
		this.fruTvaAmount = fruTvaAmount;
		this.fruSens = fruSens;
		this.feeRuleType = feeRuleType;
		this.feeSchema = feeSchema;
	}

	public FEE_RULE() {
	}

}
