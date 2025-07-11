package com.model;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Data;



@Entity
@Table(name = "WALLET_CATEGORY_OPERATION_TYPE_MAP")
@Data

public class WALLET_CATEGORY_OPERATION_TYPE_MAP {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "WCOTM_CODE")
	    private Integer id;

	    @ManyToOne
	    @JoinColumn(name = "WCOTM_OPT_CODE", referencedColumnName = "OPT_CODE")
	    @JsonBackReference("opt-wcotm")

	    private OPERATION_TYPE operationType;

	    @ManyToOne
	    @JoinColumn(name = "WCOTM_WCA_CODE", referencedColumnName = "WCA_CODE")
	    @JsonBackReference("wcat-wcotm")

	    private WALLET_CATEGORY walletCategory;

	    @Column(name = "WCOTM_LIMIT_MAX")
	    private Integer limitMax;

	    @ManyToOne
	    @JoinColumn(name = "WCOTM_FEE_CODE", referencedColumnName = "FEE_CODE", nullable = false)
	    @JsonBackReference("fee-wcotm")

	    private FEES fees;


	    @OneToOne
	    @JoinColumn(name = "WCTOM_PER_CODE", referencedColumnName = "PER_CODE", nullable = false)
	    @JsonBackReference("periodicity-wcotm")

	    private PERIODICITY periodicity;

	    @Column(name = "WCTOM_FIN_ID")
	    private Integer financialInstitutionId;

		public OPERATION_TYPE getOperationType() {
			return operationType;
		}

		public void setOperationType(OPERATION_TYPE operationType) {
			this.operationType = operationType;
		}

		public WALLET_CATEGORY getWalletCategory() {
			return walletCategory;
		}

		public void setWalletCategory(WALLET_CATEGORY walletCategory) {
			this.walletCategory = walletCategory;
		}

		public Integer getLimitMax() {
			return limitMax;
		}

		public void setLimitMax(Integer limitMax) {
			this.limitMax = limitMax;
		}

		public FEES getFees() {
			return fees;
		}

		public void setFees(FEES fees) {
			this.fees = fees;
		}

		public PERIODICITY getPeriodicity() {
			return periodicity;
		}

		public void setPeriodicity(PERIODICITY periodicity) {
			this.periodicity = periodicity;
		}

		public Integer getFinancialInstitutionId() {
			return financialInstitutionId;
		}

		public void setFinancialInstitutionId(Integer financialInstitutionId) {
			this.financialInstitutionId = financialInstitutionId;
		}

		public WALLET_CATEGORY_OPERATION_TYPE_MAP(OPERATION_TYPE operationType, WALLET_CATEGORY walletCategory,
				Integer limitMax, FEES fees, PERIODICITY periodicity, Integer financialInstitutionId) {
			super();
			this.operationType = operationType;
			this.walletCategory = walletCategory;
			this.limitMax = limitMax;
			this.fees = fees;
			this.periodicity = periodicity;
			this.financialInstitutionId = financialInstitutionId;
		}
		 public WALLET_CATEGORY_OPERATION_TYPE_MAP() {}
		

		
    }
