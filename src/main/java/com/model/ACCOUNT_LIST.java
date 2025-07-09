package com.model;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	    private Integer aliIden;

	    @Column(name = "ALI_LABE", nullable = false)
	    private String aliLabe;

	    // Clé étrangère vers WALLET
	    @ManyToOne
	    @JoinColumn(name = "ALI_WAL_CODE", nullable = true) // WALLET peut être null (0..1)
	    @JsonIgnore
	    private WALLET wallet;

	    // Relation One-to-Many avec ACCOUNT
	    @OneToMany(mappedBy = "accountList", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	   @JsonIgnore
	    private List<ACCOUNT> accounts;

	    
	     
	    public ACCOUNT_LIST(Integer aliCode, Integer aliIden, String aliLabe, WALLET wallet, List<ACCOUNT> accounts) {
			super();
			this.aliCode = aliCode;
			this.aliIden = aliIden;
			this.aliLabe = aliLabe;
			this.wallet = wallet;
			this.accounts = accounts;
		}


		public ACCOUNT_LIST()
	    {}
	    
	    
	    public Integer getAliCode() {
			return aliCode;
		}

		public void setAliCode(Integer aliCode) {
			this.aliCode = aliCode;
		}

		public Integer getAliIden() {
	        return aliIden;
	    }

	    public void setAliIden(Integer aliIden) {
	        this.aliIden = aliIden;
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
