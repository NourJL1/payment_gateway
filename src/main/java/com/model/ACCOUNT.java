package com.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	private String accIden;

	@Column(name = "ACC_KEY", nullable = false)
	private String accKey;

	@ManyToOne
	@JoinColumn(name = "ACC_ALI_CODE", referencedColumnName = "ALI_CODE", nullable = false)
  @JsonIgnoreProperties("accounts") 
	private ACCOUNT_LIST accountList;

	// Relation avec ACCOUNT_TYPE (Chaque ACCOUNT a un type d’account)
	@ManyToOne
	@JoinColumn(name = "ACC_ATY_CODE", referencedColumnName = "ATY_CODE", nullable = false)
	private ACCOUNT_TYPE accountType;

   // ✅ Nouveau champ : solde du compte
    @Column(name = "ACC_BALANCE", nullable = false)
    private Double accBalance = 0.0;

	// Relation avec BANK (Chaque ACCOUNT est lié à une seule BANK)
	@ManyToOne
	@JoinColumn(name = "ACC_BAN_CODE", referencedColumnName = "BAN_CODE", nullable = false)

	private BANK bank;

	public ACCOUNT() {
	}

  public Double getAccBalance() {
        return accBalance;
    }

    public void setAccBalance(Double accBalance) {
        this.accBalance = accBalance;
    }

	public Integer getAccCode() {
		return accCode;
	}

	public void setAccCode(Integer accCode) {
		this.accCode = accCode;
	}

	public String getAccIden() {
		return accIden;
	}

	public void setAccIden(String accIden) {
		this.accIden = accIden;
	}

	@PrePersist
	public void setAccIden() {
		this.accIden = "ACC-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmm")) + "-"
				+ UUID.randomUUID().toString().substring(0, 4).toUpperCase();
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

	public ACCOUNT(Integer accCode, String accRib, String accIden, String accKey, ACCOUNT_LIST accountList,
			ACCOUNT_TYPE accountType, BANK bank, Double accBalance) {
		super();
		this.accCode = accCode;
		this.accRib = accRib;
		this.accIden = accIden;
		this.accKey = accKey;
		this.accountList = accountList;
		this.accountType = accountType;
		this.bank = bank;
    this.accBalance = accBalance;
	}

}
