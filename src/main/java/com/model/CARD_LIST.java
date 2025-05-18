package com.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "CARD_LIST")
@Data

public class CARD_LIST {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "CLI_CODE", nullable = false, unique = true)
	    private Integer cliCode;

	    @Column(name = "CLI_IDEN", nullable = false)
	    private Integer cliIden;

	    @Column(name = "CLI_LABE", nullable = false)
	    private String cliLabe;

	    // Relation One-to-One avec WALLET
	    @OneToOne(mappedBy = "cardList") // Lien inverse
	    @JsonIgnore

	    private WALLET wallet;
	    
	    @OneToMany(mappedBy = "cardList", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<CARD> cards;

		public Integer getCliIden() {
			return cliIden;
		}

		public void setCliIden(Integer cliIden) {
			this.cliIden = cliIden;
		}

		public String getCliLabe() {
			return cliLabe;
		}

		public void setCliLabe(String cliLabe) {
			this.cliLabe = cliLabe;
		}

		public WALLET getWallet() {
			return wallet;
		}

		public void setWallet(WALLET wallet) {
			this.wallet = wallet;
		}

		public List<CARD> getCards() {
			return cards;
		}

		public void setCards(List<CARD> cards) {
			this.cards = cards;
		}

		
		public Integer getCliCode() {
			return cliCode;
		}

		public void setCliCode(Integer cliCode) {
			this.cliCode = cliCode;
		}

		

		public CARD_LIST(Integer cliCode, Integer cliIden, String cliLabe, WALLET wallet, List<CARD> cards) {
			super();
			this.cliCode = cliCode;
			this.cliIden = cliIden;
			this.cliLabe = cliLabe;
			this.wallet = wallet;
			this.cards = cards;
		}

		public CARD_LIST() {}
		
		
	    

}
