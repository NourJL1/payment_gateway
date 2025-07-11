package com.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "BANK")
@Data

public class BANK {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BAN_CODE", nullable = false, unique = true)
	private Integer banCode;

	@Column(name = "BAN_IDEN", nullable = false)
	private String banIden;

	@Column(name = "BAN_CORP_NAME", nullable = false)
	private String banCorpName;

	@Column(name = "BAN_INIT", nullable = false)
	private String banInit;

	@Column(name = "BAN_FIN_ID", nullable = false)
	private Integer banFinId;

	// Relation avec ACCOUNT (1 BANK peut être associée à plusieurs ACCOUNTS)
	@OneToMany(mappedBy = "bank", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<ACCOUNT> accounts;

	public Integer getBanCode() {
		return banCode;
	}

	public void setBanCode(Integer banCode) {
		this.banCode = banCode;
	}

	public String getBanIden() {
		return banIden;
	}

	public void setBanIden(String banIden) {
		this.banIden = banIden;
	}

	@PrePersist
	public void setBanIden() {
		this.banIden = "BAN-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmm")) + "-"
				+ UUID.randomUUID().toString().substring(0, 4).toUpperCase();
	}

	public String getBanCorpName() {
		return banCorpName;
	}

	public void setBanCorpName(String banCorpName) {
		this.banCorpName = banCorpName;
	}

	public String getBanInit() {
		return banInit;
	}

	public void setBanInit(String banInit) {
		this.banInit = banInit;
	}

	public Integer getBanFinId() {
		return banFinId;
	}

	public void setBanFinId(Integer banFinId) {
		this.banFinId = banFinId;
	}

	public List<ACCOUNT> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<ACCOUNT> accounts) {
		this.accounts = accounts;
	}

	public BANK(Integer banCode, String banIden, String banCorpName, String banInit, Integer banFinId,
			List<ACCOUNT> accounts) {
		super();
		this.banCode = banCode;
		this.banIden = banIden;
		this.banCorpName = banCorpName;
		this.banInit = banInit;
		this.banFinId = banFinId;
		this.accounts = accounts;
	}

	// Constructeur par défaut (obligatoire pour Hibernate)
	public BANK() {
	}

}
