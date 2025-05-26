package com.model;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "WALLET_TYPE")
@Data


public class WALLET_TYPE {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WTY_CODE", nullable = false, unique = true)
    private Integer wtyCode;

    @Column(name = "WTY_IDEN", nullable = false)
    private Integer wtyIden;

    @Column(name = "WTY_LABE", nullable = false)
    private String wtyLabe;

    // Relation avec WALLET (Un type peut être lié à plusieurs wallets)
    @OneToMany(mappedBy = "walletType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore

    private List<WALLET> wallets;

    
	public Integer getWtyCode() {
		return wtyCode;
	}

	public void setWtyCode(Integer wtyCode) {
		this.wtyCode = wtyCode;
	}

	public Integer getWtyIden() {
		return wtyIden;
	}

	public void setWtyIden(Integer wtyIden) {
		this.wtyIden = wtyIden;
	}

	public String getWtyLabe() {
		return wtyLabe;
	}

	public void setWtyLabe(String wtyLabe) {
		this.wtyLabe = wtyLabe;
	}

	public List<WALLET> getWallets() {
		return wallets;
	}

	public void setWallets(List<WALLET> wallets) {
		this.wallets = wallets;
	}

	
    public WALLET_TYPE(Integer wtyCode, Integer wtyIden, String wtyLabe, List<WALLET> wallets) {
		super();
		this.wtyCode = wtyCode;
		this.wtyIden = wtyIden;
		this.wtyLabe = wtyLabe;
		this.wallets = wallets;
	}

	public WALLET_TYPE() {}
    
    

}
