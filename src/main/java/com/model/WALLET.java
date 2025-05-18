package com.model;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


@Entity

@Table(name = "WALLET")
public class WALLET {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "WAL_CODE", unique = true, nullable = false)
	    private Integer walCode;

	    @Column(name = "WAL_IDEN", nullable = false)
	    private Integer walIden;

	    @Column(name = "WAL_LABE", length = 255)
	    private String walLabe;

	    @Column(name = "WAL_KEY")
	    private Integer walKey;

	    @Column(name = "WAL_EFF_BAL")
	    private Float walEffBal;

	    @Column(name = "WAL_LOGIC_BALANCE")
	    private Float walLogicBalance;

	    @Column(name = "WAL_SPECIFIC_BALANCE")
	    private Float walSpecificBalance;

	    @Column(name = "LAST_UPDATED_DATE")
	    private LocalDateTime lastUpdatedDate;
	    
	    @PreUpdate
	    @PrePersist
	    private void updateLastUpdatedDate() {
	        this.lastUpdatedDate = LocalDateTime.now();
	    }

	    
	    @Column(name= "WAL_FIN_ID")
	    private Integer walFinId;

	    // Relation *..1 avec CUSTOMER
	    @ManyToOne
	    @JoinColumn(name = "WAL_CUS_CODE", referencedColumnName = "CUS_CODE")
	    @JsonIgnore
	    private CUSTOMER customer;

	    // Relation *..1 avec WALLET_STATUS
	    @ManyToOne
	    @JoinColumn(name = "WAL_WST_CODE", referencedColumnName = "WST_CODE")
	    


	    private WALLET_STATUS walletStatus;

	    // Relation *..1 avec WALLET_TYPE
	    @ManyToOne
	    @JoinColumn(name = "WAL_WTY_CODE", referencedColumnName = "WTY_CODE")
	    private WALLET_TYPE walletType;

	    // Relation *..1 avec WALLET_CATEGORY
	    @ManyToOne
	    @JoinColumn(name = "WAL_WCA_CODE", referencedColumnName = "WCA_CODE")

	    
	    private WALLET_CATEGORY walletCategory;

	    // Relation 1..0-* avec WALLET_OPERATIONS
	    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    @JsonIgnore
	    private List<WALLET_OPERATIONS> walletOperations;
	    
	    

	 // One-to-One : Dernier solde historique
	    @OneToOne
	    @JoinColumn(name = "WAL_WBH_CODE", referencedColumnName = "WBH_CODE", nullable = true)

	    private WALLET_BALANCE_HISTORY lastBalanceHistory;

	    // Relation 0..1 → 0..* avec OPERATION_TYPE
	    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	   @JsonIgnore

	    private List<OPERATION_TYPE> operationTypes;

	    @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "WAL_CLI_CODE", referencedColumnName = "CLI_CODE", nullable = true) 
	    private CARD_LIST cardList;



	    // Relation 0..1 → * avec ACCOUNT_LIST
	    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)

	    private List<ACCOUNT_LIST> accountList;

	    // Relation avec WALLET_OPERATION_TYPE_MAP (Fix mapping issue)
	    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    @JsonManagedReference("wallet-walletOp")
	    private List<WALLET_OPERATION_TYPE_MAP> walletOperationTypeMappings;
	    
	    

		public Integer getWalIden() {
			return walIden;
		}

		public void setWalIden(Integer walIden) {
			this.walIden = walIden;
		}

		public String getWalLabe() {
			return walLabe;
		}

		public void setWalLabe(String walLabe) {
			this.walLabe = walLabe;
		}

		public Integer getWalKey() {
			return walKey;
		}

		public void setWalKey(Integer walKey) {
			this.walKey = walKey;
		}

		public Float getWalEffBal() {
			return walEffBal;
		}

		public void setWalEffBal(Float walEffBal) {
		    this.walEffBal = walEffBal;
		    updateLastUpdatedDate();  // Mise à jour de la date à chaque modification
		}

		public Float getWalLogicBalance() {
			return walLogicBalance;
		}

		public void setWalLogicBalance(Float walLogicBalance) {
		    this.walLogicBalance = walLogicBalance;
		    updateLastUpdatedDate();  // Mise à jour de la date à chaque modification
		}

		public Float getWalSpecificBalance() {
			return walSpecificBalance;
		}

		public void setWalSpecificBalance(Float walSpecificBalance) {
		    this.walSpecificBalance = walSpecificBalance;
		    updateLastUpdatedDate();  // Mise à jour de la date à chaque modification
		}

		
		public Integer getWalCode() {
			return walCode;
		}

		public void setWalCode(Integer walCode) {
			this.walCode = walCode;
		}

		public LocalDateTime getLastUpdatedDate() {
			return lastUpdatedDate;
		}

		public void setLastUpdatedDate(LocalDateTime lastUpdatedDate) {
			this.lastUpdatedDate = lastUpdatedDate;
		}

		public CUSTOMER getCustomer() {
			return customer;
		}

		public void setCustomer(CUSTOMER customer) {
			this.customer = customer;
		}

		public WALLET_STATUS getWalletStatus() {
			return walletStatus;
		}

		public void setWalletStatus(WALLET_STATUS walletStatus) {
			this.walletStatus = walletStatus;
		}

		public WALLET_TYPE getWalletType() {
			return walletType;
		}

		public void setWalletType(WALLET_TYPE walletType) {
			this.walletType = walletType;
		}

		public WALLET_CATEGORY getWalletCategory() {
			return walletCategory;
		}

		public void setWalletCategory(WALLET_CATEGORY walletCategory) {
			this.walletCategory = walletCategory;
		}

		public List<WALLET_OPERATIONS> getWalletOperations() {
			return walletOperations;
		}

		public void setWalletOperations(List<WALLET_OPERATIONS> walletOperations) {
			this.walletOperations = walletOperations;
		}

		

		/*public List<WALLET_BALANCE_HISTORY> getBalanceHistories() {
			return balanceHistories;
		}

		public void setBalanceHistories(List<WALLET_BALANCE_HISTORY> balanceHistories) {
			this.balanceHistories = balanceHistories;
		}*/

		public WALLET_BALANCE_HISTORY getLastBalanceHistory() {
			return lastBalanceHistory;
		}

		public void setLastBalanceHistory(WALLET_BALANCE_HISTORY lastBalanceHistory) {
			this.lastBalanceHistory = lastBalanceHistory;
		}

		public List<OPERATION_TYPE> getOperationTypes() {
			return operationTypes;
		}

		public void setOperationTypes(List<OPERATION_TYPE> operationTypes) {
			this.operationTypes = operationTypes;
		}

		public CARD_LIST getCardList() {
			return cardList;
		}

		public void setCardList(CARD_LIST cardList) {
			this.cardList = cardList;
		}

		public List<ACCOUNT_LIST> getAccountList() {
			return accountList;
		}

		public void setAccountList(List<ACCOUNT_LIST> accountList) {
			this.accountList = accountList;
		}

		public List<WALLET_OPERATION_TYPE_MAP> getWalletOperationTypeMappings() {
			return walletOperationTypeMappings;
		}

		public void setWalletOperationTypeMappings(List<WALLET_OPERATION_TYPE_MAP> walletOperationTypeMappings) {
			this.walletOperationTypeMappings = walletOperationTypeMappings;
		}

		public Integer getWalFinId() {
			return walFinId;
		}

		public void setWalFinId(Integer walFinId) {
			this.walFinId = walFinId;
		}

		
		public WALLET(Integer walCode, Integer walIden, String walLabe, Integer walKey, Float walEffBal,
				Float walLogicBalance, Float walSpecificBalance, LocalDateTime lastUpdatedDate, Integer walFinId,
				CUSTOMER customer, WALLET_STATUS walletStatus, WALLET_TYPE walletType, WALLET_CATEGORY walletCategory,
				List<WALLET_OPERATIONS> walletOperations, WALLET_BALANCE_HISTORY lastBalanceHistory,
				List<OPERATION_TYPE> operationTypes, CARD_LIST cardList, List<ACCOUNT_LIST> accountList,
				List<WALLET_OPERATION_TYPE_MAP> walletOperationTypeMappings) {
			super();
			this.walCode = walCode;
			this.walIden = walIden;
			this.walLabe = walLabe;
			this.walKey = walKey;
			this.walEffBal = walEffBal;
			this.walLogicBalance = walLogicBalance;
			this.walSpecificBalance = walSpecificBalance;
			this.lastUpdatedDate = lastUpdatedDate;
			this.walFinId = walFinId;
			this.customer = customer;
			this.walletStatus = walletStatus;
			this.walletType = walletType;
			this.walletCategory = walletCategory;
			this.walletOperations = walletOperations;
			this.lastBalanceHistory = lastBalanceHistory;
			this.operationTypes = operationTypes;
			this.cardList = cardList;
			this.accountList = accountList;
			this.walletOperationTypeMappings = walletOperationTypeMappings;
		}

		public WALLET() {}
		

}