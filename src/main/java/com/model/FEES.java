package com.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "FEES")
@Data


public class FEES {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "FEE_CODE", nullable = false, unique = true)
	    private Integer feeCode;

	 
	    @Column(name = "FEE_IDEN")
	    private String feeIden;

	    @Column(name = "FEE_LABE")
	    private String feeLabel;

	    @Column(name = "FEE_MIN_LIMIT")
	    private Float feeMinLimit;

	    @Column(name = "FEE_AMOUNT")
	    private Float feeAmount;

	    @Column(name = "FEE_MAX_LIMIT")
	    private Float feeMaxLimit;

	    @Column(name = "FEE_PERCENTAGE")
	    private String feePercentage;

	    @Column(name = "FEE_MAX_AMOUNT")
	    private Float feeMaxAmount;

	    @Column(name = "FEE_FIN_ID")
	    private Integer financialInstitutionId;

	    @OneToMany(mappedBy = "fees")
	    @JsonManagedReference("fee-wcotm")

	    private List<WALLET_CATEGORY_OPERATION_TYPE_MAP> walletCategoryOperationTypeMaps;

	    @OneToMany(mappedBy = "fees")
	    @JsonManagedReference("fees-walletOp")

	    private List<WALLET_OPERATION_TYPE_MAP> walletOperationTypeMaps;
	    
	    

		public Integer getFeeCode() {
			return feeCode;
		}

		public void setFeeCode(Integer feeCode) {
			this.feeCode = feeCode;
		}

		public String getFeeIden() {
			return feeIden;
		}

		public void setFeeIden(String feeIden) {
			this.feeIden = feeIden;
		}

		public String getFeeLabel() {
			return feeLabel;
		}

		public void setFeeLabel(String feeLabel) {
			this.feeLabel = feeLabel;
		}

		public Float getFeeMinLimit() {
			return feeMinLimit;
		}

		public void setFeeMinLimit(Float feeMinLimit) {
			this.feeMinLimit = feeMinLimit;
		}

		public Float getFeeAmount() {
			return feeAmount;
		}

		public void setFeeAmount(Float feeAmount) {
			this.feeAmount = feeAmount;
		}

		public Float getFeeMaxLimit() {
			return feeMaxLimit;
		}

		public void setFeeMaxLimit(Float feeMaxLimit) {
			this.feeMaxLimit = feeMaxLimit;
		}

		public String getFeePercentage() {
			return feePercentage;
		}

		public void setFeePercentage(String feePercentage) {
			this.feePercentage = feePercentage;
		}

		public Float getFeeMaxAmount() {
			return feeMaxAmount;
		}

		public void setFeeMaxAmount(Float feeMaxAmount) {
			this.feeMaxAmount = feeMaxAmount;
		}

		public Integer getFinancialInstitutionId() {
			return financialInstitutionId;
		}

		public void setFinancialInstitutionId(Integer financialInstitutionId) {
			this.financialInstitutionId = financialInstitutionId;
		}

		public List<WALLET_CATEGORY_OPERATION_TYPE_MAP> getWalletCategoryOperationTypeMaps() {
			return walletCategoryOperationTypeMaps;
		}

		public void setWalletCategoryOperationTypeMaps(
				List<WALLET_CATEGORY_OPERATION_TYPE_MAP> walletCategoryOperationTypeMaps) {
			this.walletCategoryOperationTypeMaps = walletCategoryOperationTypeMaps;
		}

		public List<WALLET_OPERATION_TYPE_MAP> getWalletOperationTypeMaps() {
			return walletOperationTypeMaps;
		}

		public void setWalletOperationTypeMaps(List<WALLET_OPERATION_TYPE_MAP> walletOperationTypeMaps) {
			this.walletOperationTypeMaps = walletOperationTypeMaps;
		}

		
		public FEES(Integer feeCode, String feeIden, String feeLabel, Float feeMinLimit, Float feeAmount,
				Float feeMaxLimit, String feePercentage, Float feeMaxAmount, Integer financialInstitutionId,
				List<WALLET_CATEGORY_OPERATION_TYPE_MAP> walletCategoryOperationTypeMaps,
				List<WALLET_OPERATION_TYPE_MAP> walletOperationTypeMaps) {
			super();
			this.feeCode = feeCode;
			this.feeIden = feeIden;
			this.feeLabel = feeLabel;
			this.feeMinLimit = feeMinLimit;
			this.feeAmount = feeAmount;
			this.feeMaxLimit = feeMaxLimit;
			this.feePercentage = feePercentage;
			this.feeMaxAmount = feeMaxAmount;
			this.financialInstitutionId = financialInstitutionId;
			this.walletCategoryOperationTypeMaps = walletCategoryOperationTypeMaps;
			this.walletOperationTypeMaps = walletOperationTypeMaps;
		}

		public FEES() {}
		

}
