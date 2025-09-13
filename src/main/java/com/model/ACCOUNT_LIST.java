package com.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ACCOUNT_LIST")
@Data
public class ACCOUNT_LIST {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ALI_CODE", nullable = false, unique = true)
	private Integer aliCode;

	@Column(name = "ALI_IDEN", nullable = false)
	private String aliIden;

	@Column(name = "ALI_LABE", nullable = false)
	private String aliLabe;

	// Clé étrangère vers WALLET
	@OneToOne(mappedBy = "accountList") // Lien inverse
	    @JsonIgnore

	    private WALLET wallet;

	// Relation One-to-Many avec ACCOUNT
	@OneToMany(mappedBy = "accountList", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnoreProperties("accountList")
	private List<ACCOUNT> accounts;

	public ACCOUNT_LIST(Integer aliCode, String aliIden, String aliLabe, WALLET wallet, List<ACCOUNT> accounts) {
		super();
		this.aliCode = aliCode;
		this.aliIden = aliIden;
		this.aliLabe = aliLabe;
		this.wallet = wallet;
		this.accounts = accounts;
	}

	public ACCOUNT_LIST() {
	}

	public Integer getAliCode() {
		return aliCode;
	}

	public void setAliCode(Integer aliCode) {
		this.aliCode = aliCode;
	}

	public String getAliIden() {
		return aliIden;
	}

	public void setAliIden(String aliIden) {
		this.aliIden = aliIden;
	}

	@PrePersist
	public void setAliIden() {
		this.aliIden = "ALI-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmm")) + "-"
				+ UUID.randomUUID().toString().substring(0, 4).toUpperCase();
	}

	public String getAliLabe() {
		return aliLabe;
	}

	public void setAliLabe(String aliLabe) {
		this.aliLabe = aliLabe;
	}

	public WALLET getWallet() {
		return wallet;
	}

	public void setWallet(WALLET wallet) {
		this.wallet = wallet;
	}

	public List<ACCOUNT> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<ACCOUNT> accounts) {
		this.accounts = accounts;
	}

}
