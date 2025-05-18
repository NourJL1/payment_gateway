package com.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "WALLET_STATUS")  // Fix the table name
@Data

public class WALLET_STATUS {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "WST_CODE", nullable = false, unique = true)
	    private Integer wstCode;

	    @Column(name = "WST_IDEN", nullable = false)
	    private Integer wstIden;

	    @Column(name = "WST_LABE", nullable = false)
	    private String wstLabe;

	    // Relation 1 → 0..* avec WALLET
	    @OneToMany(mappedBy = "walletStatus", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    @JsonIgnore
	    private List<WALLET> wallets;
	    
	    

		public Integer getWstCode() {
			return wstCode;
		}

		public void setWstCode(Integer wstCode) {
			this.wstCode = wstCode;
		}

		public Integer getWstIden() {
			return wstIden;
		}

		public void setWstIden(Integer wstIden) {
			this.wstIden = wstIden;
		}

		public String getWstLabe() {
			return wstLabe;
		}

		public void setWstLabe(String wstLabe) {
			this.wstLabe = wstLabe;
		}

		public List<WALLET> getWallets() {
			return wallets;
		}

		public void setWallets(List<WALLET> wallets) {
			this.wallets = wallets;
		}

		
	    
	    public WALLET_STATUS(Integer wstCode, Integer wstIden, String wstLabe, List<WALLET> wallets) {
			super();
			this.wstCode = wstCode;
			this.wstIden = wstIden;
			this.wstLabe = wstLabe;
			this.wallets = wallets;
		}

		public WALLET_STATUS() {}
	    
	}
