package com.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "WALLET_BALANCE_HISTORY")
@Data

public class WALLET_BALANCE_HISTORY {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WBH_CODE", nullable = false, unique = true)
    private Integer wbhCode;

    @Column(name = "WBH_IDEN", nullable = false)
    private String wbhIden;

    @Column(name = "WBH_EFF_BAL")
    private Float wbhEffBal;

    @Column(name = "WBH_LOGIC_BALANCE")
    private Float wbhLogicBalance;

    @Column(name = "WBH_SPECIFIC_BALANCE")
    private Float wbhSpecificBalance;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "WBH_LAST_UPDATED", nullable = false)
    private Date wbhLastUpdated;

    // Relation avec WALLET
    @ManyToOne
    @JoinColumn(name = "WBH_WAL_CODE", referencedColumnName = "WAL_CODE", nullable = false)
    @JsonIgnore

    private WALLET wallet;

	

	
	public WALLET_BALANCE_HISTORY(Integer wbhCode, String wbhIden, Float wbhEffBal, Float wbhLogicBalance,
			Float wbhSpecificBalance, Date wbhLastUpdated, WALLET wallet) {
		super();
		this.wbhCode = wbhCode;
		this.wbhIden = wbhIden;
		this.wbhEffBal = wbhEffBal;
		this.wbhLogicBalance = wbhLogicBalance;
		this.wbhSpecificBalance = wbhSpecificBalance;
		this.wbhLastUpdated = wbhLastUpdated;
		this.wallet = wallet;
	}




	public Integer getWbhCode() {
		return wbhCode;
	}




	public void setWbhCode(Integer wbhCode) {
		this.wbhCode = wbhCode;
	}




	public String getWbhIden() {
		return wbhIden;
	}




	public void setWbhIden(String wbhIden) {
		this.wbhIden = wbhIden;
	}




	public Float getWbhEffBal() {
		return wbhEffBal;
	}




	public void setWbhEffBal(Float wbhEffBal) {
		this.wbhEffBal = wbhEffBal;
	}




	public Float getWbhLogicBalance() {
		return wbhLogicBalance;
	}




	public void setWbhLogicBalance(Float wbhLogicBalance) {
		this.wbhLogicBalance = wbhLogicBalance;
	}




	public Float getWbhSpecificBalance() {
		return wbhSpecificBalance;
	}




	public void setWbhSpecificBalance(Float wbhSpecificBalance) {
		this.wbhSpecificBalance = wbhSpecificBalance;
	}




	public Date getWbhLastUpdated() {
		return wbhLastUpdated;
	}




	public void setWbhLastUpdated(Date wbhLastUpdated) {
		this.wbhLastUpdated = wbhLastUpdated;
	}




	public WALLET getWallet() {
		return wallet;
	}




	public void setWallet(WALLET wallet) {
		this.wallet = wallet;
	}




	public WALLET_BALANCE_HISTORY() {}
    
    
    

}
