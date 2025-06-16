package com.model;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "WALLET_CATEGORY")
@Data

public class WALLET_CATEGORY {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WCA_CODE", nullable = false, unique = true)
    private Integer wcaCode;

    @Column(name = "WCA_IDEN", nullable = false)
    private String wcaIden;

    @Column(name = "WCA_LABE", nullable = false)
    private String wcaLabe;

    @Column(name = "WCA_FIN_ID", nullable = false)
    private Integer wcaFinId;

    // Relation 1 → 0..* avec WALLET
    @OneToMany(mappedBy = "walletCategory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore

    private List<WALLET> wallets;

    
    
    @OneToMany(mappedBy = "walletCategory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("wcat-wcotm")

    private List<WALLET_CATEGORY_OPERATION_TYPE_MAP> walletCategoryOperationTypeMappings;



	public Integer getWcaCode() {
		return wcaCode;
	}



	public void setWcaCode(Integer wcaCode) {
		this.wcaCode = wcaCode;
	}



	public String getWcaIden() {
		return wcaIden;
	}



	public void setWcaIden(String wcaIden) {
		this.wcaIden = wcaIden;
	}



	public String getWcaLabe() {
		return wcaLabe;
	}



	public void setWcaLabe(String wcaLabe) {
		this.wcaLabe = wcaLabe;
	}



	public Integer getWcaFinId() {
		return wcaFinId;
	}



	public void setWcaFinId(Integer wcaFinId) {
		this.wcaFinId = wcaFinId;
	}



	public List<WALLET> getWallets() {
		return wallets;
	}



	public void setWallets(List<WALLET> wallets) {
		this.wallets = wallets;
	}



	public List<WALLET_CATEGORY_OPERATION_TYPE_MAP> getWalletCategoryOperationTypeMappings() {
		return walletCategoryOperationTypeMappings;
	}



	public void setWalletCategoryOperationTypeMappings(
			List<WALLET_CATEGORY_OPERATION_TYPE_MAP> walletCategoryOperationTypeMappings) {
		this.walletCategoryOperationTypeMappings = walletCategoryOperationTypeMappings;
	}



	
	public WALLET_CATEGORY(Integer wcaCode, String wcaIden, String wcaLabe, Integer wcaFinId, List<WALLET> wallets,
			List<WALLET_CATEGORY_OPERATION_TYPE_MAP> walletCategoryOperationTypeMappings) {
		super();
		this.wcaCode = wcaCode;
		this.wcaIden = wcaIden;
		this.wcaLabe = wcaLabe;
		this.wcaFinId = wcaFinId;
		this.wallets = wallets;
		this.walletCategoryOperationTypeMappings = walletCategoryOperationTypeMappings;
	}



	public WALLET_CATEGORY() {}
   

}
