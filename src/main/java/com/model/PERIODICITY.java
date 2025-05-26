package com.model;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "PERIODICITY")
@Data
@AllArgsConstructor

public class PERIODICITY {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PER_CODE", nullable = false, unique = true)
    private Integer perCode;

    @Column(name = "PER_IDEN", nullable = false)
    private Integer perIden;

    @Column(name = "PER_LABE", nullable = false)
    private String perLabe;

    @OneToMany(mappedBy = "periodicity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("periodicity-wcotm")

    private List<WALLET_CATEGORY_OPERATION_TYPE_MAP> walletCategoryOperationTypeMaps;
    
    @OneToMany(mappedBy = "periodicity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("periodicity-walletOp")
    private List<WALLET_OPERATION_TYPE_MAP> walletOperationTypeMaps;

	public Integer getPerIden() {
		return perIden;
	}

	public void setPerIden(Integer perIden) {
		this.perIden = perIden;
	}

	public String getPerLabe() {
		return perLabe;
	}

	public void setPerLabe(String perLabe) {
		this.perLabe = perLabe;
	}
	
	

	public Integer getPerCode() {
		return perCode;
	}

	public void setPerCode(Integer perCode) {
		this.perCode = perCode;
	}

	public List<WALLET_CATEGORY_OPERATION_TYPE_MAP> getWalletCategoryOperationTypeMaps() {
		return walletCategoryOperationTypeMaps;
	}

	public void setWalletCategoryOperationTypeMaps(
			List<WALLET_CATEGORY_OPERATION_TYPE_MAP> walletCategoryOperationTypeMaps) {
		this.walletCategoryOperationTypeMaps = walletCategoryOperationTypeMaps;
	}
	

	public List<WALLET_OPERATION_TYPE_MAP> getWalletOperationTypeMaps() {
		return walletOperationTypeMaps;
	}

	public void setWalletOperationTypeMaps(List<WALLET_OPERATION_TYPE_MAP> walletOperationTypeMaps) {
		this.walletOperationTypeMaps = walletOperationTypeMaps;
	}

	

public PERIODICITY() {}
	


}
