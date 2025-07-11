package com.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "WALLET_OPERATION_TYPE_MAP")
@Data
public class WALLET_OPERATION_TYPE_MAP {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WOTM_CODE", nullable = false, unique = true)
    private Integer wotmCode;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "WOTM_OPT_CODE", nullable = false)
    @JsonBackReference("operationType-walletOp")

    private OPERATION_TYPE operationType;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "WOTM_WAL_CODE", nullable = false)
    @JsonBackReference("wallet-walletOp")

    private WALLET wallet;

    @Column(name = "WOTM_LIMIT_MAX")
    private Float wotmLimitMax;

    @Column(name = "WOTM_DISP_AMOUNT")
    private Float wotmDispAmount;
    
    @ManyToOne
    @JoinColumn(name = "WOTM_PER_CODE", referencedColumnName = "PER_CODE", nullable = false)
    @JsonBackReference("periodicity-walletOp")

    private PERIODICITY periodicity;

    
    @ManyToOne
    @JoinColumn(name = "WOTM_FEE_CODE", referencedColumnName = "FEE_CODE", nullable = false)
    @JsonBackReference("fees-walletOp")

    private FEES fees;
    
    


	public OPERATION_TYPE getOperationType() {
		return operationType;
	}


	public void setOperationType(OPERATION_TYPE operationType) {
		this.operationType = operationType;
	}


	public WALLET getWallet() {
		return wallet;
	}


	public void setWallet(WALLET wallet) {
		this.wallet = wallet;
	}


	public Float getWotmLimitMax() {
		return wotmLimitMax;
	}


	public void setWotmLimitMax(Float wotmLimitMax) {
		this.wotmLimitMax = wotmLimitMax;
	}


	public Float getWotmDispAmount() {
		return wotmDispAmount;
	}


	public void setWotmDispAmount(Float wotmDispAmount) {
		this.wotmDispAmount = wotmDispAmount;
	}





	public PERIODICITY getPeriodicity() {
		return periodicity;
	}


	public void setPeriodicity(PERIODICITY periodicity) {
		this.periodicity = periodicity;
	}


	public FEES getFees() {
		return fees;
	}


	public void setFees(FEES fees) {
		this.fees = fees;
	}


	public WALLET_OPERATION_TYPE_MAP(OPERATION_TYPE operationType, WALLET wallet, Float wotmLimitMax,
			Float wotmDispAmount, PERIODICITY periodicity, FEES fees) {
		super();
		this.operationType = operationType;
		this.wallet = wallet;
		this.wotmLimitMax = wotmLimitMax;
		this.wotmDispAmount = wotmDispAmount;
		
		this.periodicity = periodicity;
		this.fees = fees;
	}
    public WALLET_OPERATION_TYPE_MAP () {}
    


	
    
	    
}
