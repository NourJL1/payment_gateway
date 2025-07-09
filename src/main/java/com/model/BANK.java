package com.model;
import java.util.List;

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
    private Integer banIden;

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

	public Integer getBanIden() {
		return banIden;
	}

	public void setBanIden(Integer banIden) {
		this.banIden = banIden;
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

	public BANK(Integer banCode, Integer banIden, String banCorpName, String banInit, Integer banFinId,
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
