package com.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ACCOUNT")
@Data

public class ACCOUNT {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACC_CODE", nullable = false, unique = true)
    private Integer accCode;

    @Column(name = "ACC_RIB", nullable = false)
    private String accRib;
    
    @Column(name = "ACC_IDEN", nullable = false)
    private Integer accIden;

    @Column(name = "ACC_KEY", nullable = false)
    private String accKey;

    @ManyToOne
    @JoinColumn(name = "ACC_ALI_CODE", referencedColumnName = "ALI_CODE", nullable = false)
    private ACCOUNT_LIST accountList;


    // Relation avec ACCOUNT_TYPE (Chaque ACCOUNT a un type d’account)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ACC_ATY_CODE", referencedColumnName = "ATY_CODE", nullable = false)
    private ACCOUNT_TYPE accountType;

    // Relation avec BANK (Chaque ACCOUNT est lié à une seule BANK)
    @ManyToOne
    @JoinColumn(name = "ACC_BAN_CODE", referencedColumnName = "BAN_CODE", nullable = false)

    private BANK bank;
    
    public ACCOUNT(){}
    
    

	public Integer getAccCode() {
		return accCode;
	}



	public void setAccCode(Integer accCode) {
		this.accCode = accCode;
	}



	public Integer getAccIden() {
		return accIden;
	}



	public void setAccIden(Integer accIden) {
		this.accIden = accIden;
	}



	public String getAccRib() {
		return accRib;
	}

	public void setAccRib(String accRib) {
		this.accRib = accRib;
	}

	public String getAccKey() {
		return accKey;
	}

	public void setAccKey(String accKey) {
		this.accKey = accKey;
	}

	public ACCOUNT_LIST getAccountList() {
		return accountList;
	}

	public void setAccountList(ACCOUNT_LIST accountList) {
		this.accountList = accountList;
	}

	public ACCOUNT_TYPE getAccountType() {
		return accountType;
	}

	public void setAccountType(ACCOUNT_TYPE accountType) {
		this.accountType = accountType;
	}

	public BANK getBank() {
		return bank;
	}

	public void setBank(BANK bank) {
		this.bank = bank;
	}

	public ACCOUNT(Integer accCode, String accRib, Integer accIden, String accKey, ACCOUNT_LIST accountList,
			ACCOUNT_TYPE accountType, BANK bank) {
		super();
		this.accCode = accCode;
		this.accRib = accRib;
		this.accIden = accIden;
		this.accKey = accKey;
		this.accountList = accountList;
		this.accountType = accountType;
		this.bank = bank;
	}

	
	
    
    
    
    

}
