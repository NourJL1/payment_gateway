package com.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "WALLET_OPERATIONS")
@Getter
@Setter

public class WALLET_OPERATIONS {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "WOP_CODE") // Nom de colonne en base reste en majuscule
	private Integer wopCode;


    @Column(name = "WOP_IDEN", nullable = false)
    private Integer wopIden;

    @ManyToOne
    @JoinColumn(name = "WOP_WAL_CODE", referencedColumnName = "WAL_CODE", nullable = false)
    private WALLET wallet;  // 🔹 Relation avec WALLET

    //@Column(name = "WOP_CUS_CODE", nullable = false)
    //private Integer WOP_CUS_CODE;

    @Column(name = "WOP_OTY_CODE", nullable = false)
    private Integer wopOtyCode;

    @Column(name = "WOP_AMOUNT", nullable = false)
    private Float wopAmount;

    @Column(name = "WOP_CURRENCY", length = 10, nullable = false)
    private String wopCurrency;

    @Column(name = "WOP_STATUS", length = 20, nullable = false)
    private String wopStatus;

    @Column(name = "WOP_LABEL", length = 255)
    private String wopLabel;

    @Column(name = "WOP_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date wopTimestamps;
    @JsonIgnore
    @OneToMany(mappedBy = "walletOperation", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<OPERATION_DETAILS> operationDetails;
    
    
    
    @ManyToOne
    @JoinColumn(name = "WOP_CUS_CODE", referencedColumnName = "CUS_CODE")
    @JsonIgnore
    private CUSTOMER customer;


	

	


	public WALLET_OPERATIONS(Integer wopCode, Integer wopIden, WALLET wallet, Integer wopOtyCode, Float wopAmount,
			String wopCurrency, String wopStatus, String wopLabel, Date wopTimestamps,
			List<OPERATION_DETAILS> operationDetails, CUSTOMER customer) {
		super();
		this.wopCode = wopCode;
		this.wopIden = wopIden;
		this.wallet = wallet;
		this.wopOtyCode = wopOtyCode;
		this.wopAmount = wopAmount;
		this.wopCurrency = wopCurrency;
		this.wopStatus = wopStatus;
		this.wopLabel = wopLabel;
		this.wopTimestamps = wopTimestamps;
		this.operationDetails = operationDetails;
		this.customer = customer;
	}



	public CUSTOMER getCustomer() {
		return customer;
	}



	public void setCustomer(CUSTOMER customer) {
		this.customer = customer;
	}



	public Integer getWopCode() {
		return wopCode;
	}



	public void setWopCode(Integer wopCode) {
		this.wopCode = wopCode;
	}



	public Integer getWopIden() {
		return wopIden;
	}



	public void setWopIden(Integer wopIden) {
		this.wopIden = wopIden;
	}



	public WALLET getWallet() {
		return wallet;
	}



	public void setWallet(WALLET wallet) {
		this.wallet = wallet;
	}



	public Integer getWopOtyCode() {
		return wopOtyCode;
	}



	public void setWopOtyCode(Integer wopOtyCode) {
		this.wopOtyCode = wopOtyCode;
	}



	public Float getWopAmount() {
		return wopAmount;
	}



	public void setWopAmount(Float wopAmount) {
		this.wopAmount = wopAmount;
	}



	public String getWopCurrency() {
		return wopCurrency;
	}



	public void setWopCurrency(String wopCurrency) {
		this.wopCurrency = wopCurrency;
	}



	public String getWopStatus() {
		return wopStatus;
	}



	public void setWopStatus(String wopStatus) {
		this.wopStatus = wopStatus;
	}



	public String getWopLabel() {
		return wopLabel;
	}



	public void setWopLabel(String wopLabel) {
		this.wopLabel = wopLabel;
	}



	public Date getWopTimestamps() {
		return wopTimestamps;
	}



	public void setWopTimestamps(Date wopTimestamps) {
		this.wopTimestamps = wopTimestamps;
	}



	public List<OPERATION_DETAILS> getOperationDetails() {
		return operationDetails;
	}



	public void setOperationDetails(List<OPERATION_DETAILS> operationDetails) {
		this.operationDetails = operationDetails;
	}



	public WALLET_OPERATIONS() {}

	
}
