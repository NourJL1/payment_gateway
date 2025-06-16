package com.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name = "OPERATION_DETAILS")
@Data

public class OPERATION_DETAILS {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "ODE_CODE", nullable = false, unique = true)
	    private Integer odeCode;

	   
	    @Column(name = "ODE_CUS_CODE", nullable = false)
	    private Integer odeCusCode;

      @Column(name = "ODE_IDEN", nullable = false)
	    private String odeIden;

	    @Column(name = "ODE_TYPE", nullable = false)
	    private String odeType;

	    @Column(name = "ODE_VALUE", nullable = false)
	    private String odeValue;

	    @Column(name = "ODE_FEE_AMOUNT")
	    private Float odeFeeAmount;

	    @Column(name = "ODE_PAY_METH", nullable = false)
	    private String odePayMeth;

	    @Column(name = "ODE_RECIPIENT_WALLET", nullable = false)
	    private String odeRecipientWallet;

	    @Temporal(TemporalType.TIMESTAMP)
	    @Column(name = "ODE_CREATED_AT", nullable = false, updatable = false)
	    private Date odeCreatedAt;
	    
	    @ManyToOne
	    @JoinColumn(name = "ODE_WOP_CODE", referencedColumnName = "WOP_CODE", nullable = false)
	    private WALLET_OPERATIONS walletOperation;



	    public Integer getOdeCode() {
			return odeCode;
		}


		public void setOdeCode(Integer odeCode) {
			this.odeCode = odeCode;
		}


		@PrePersist
	    protected void onCreate() {
	        this.odeCreatedAt = new Date();
	    }


		public Integer getOdeCusCode() {
			return odeCusCode;
		}


		public void setOdeCusCode(Integer odeCusCode) {
			this.odeCusCode = odeCusCode;
		}


		public String getOdeType() {
			return odeType;
		}


		public void setOdeType(String odeType) {
			this.odeType = odeType;
		}


		public String getOdeValue() {
			return odeValue;
		}


		public void setOdeValue(String odeValue) {
			this.odeValue = odeValue;
		}


		public Float getOdeFeeAmount() {
			return odeFeeAmount;
		}


		public void setOdeFeeAmount(Float odeFeeAmount) {
			this.odeFeeAmount = odeFeeAmount;
		}


		public String getOdePayMeth() {
			return odePayMeth;
		}


		public void setOdePayMeth(String odePayMeth) {
			this.odePayMeth = odePayMeth;
		}


		public String getOdeRecipientWallet() {
			return odeRecipientWallet;
		}


		public void setOdeRecipientWallet(String odeRecipientWallet) {
			this.odeRecipientWallet = odeRecipientWallet;
		}


		public Date getOdeCreatedAt() {
			return odeCreatedAt;
		}


		public void setOdeCreatedAt(Date odeCreatedAt) {
			this.odeCreatedAt = odeCreatedAt;
		}


		public WALLET_OPERATIONS getWalletOperation() {
			return walletOperation;
		}


		public void setWalletOperation(WALLET_OPERATIONS walletOperation) {
			this.walletOperation = walletOperation;
		}

    
		public String getOdeIden() {
			return odeIden;
		}


		public void setOdeIden(String odeIden) {
			this.odeIden = odeIden;
		}


	public OPERATION_DETAILS(Integer odeCode, Integer odeCusCode, String odeIden, String odeType, String odeValue,
				Float odeFeeAmount, String odePayMeth, String odeRecipientWallet, Date odeCreatedAt,
				WALLET_OPERATIONS walletOperation) {
			super();
			this.odeCode = odeCode;
			this.odeCusCode = odeCusCode;
			this.odeIden = odeIden;
			this.odeType = odeType;
			this.odeValue = odeValue;
			this.odeFeeAmount = odeFeeAmount;
			this.odePayMeth = odePayMeth;
			this.odeRecipientWallet = odeRecipientWallet;
			this.odeCreatedAt = odeCreatedAt;
			this.walletOperation = walletOperation;
		}
		
		public OPERATION_DETAILS() {}
	    
	    


}