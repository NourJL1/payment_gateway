package com.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "CARD_TYPE")
@Data

public class CARD_TYPE {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "CTYP_CODE", nullable = false, unique = true)
	    private Integer ctypCode;

	    @Column(name = "CTYP_IDEN", nullable = false)
	    private Integer ctypIden;

	    @Column(name = "CTYP_LABE", nullable = false)
	    private String ctypLabe;

		public Integer getCtypIden() {
			return ctypIden;
			
		}
		@OneToMany(mappedBy = "cardType", cascade = CascadeType.ALL, orphanRemoval = true)
		private List<CARD> cards;

		public String getCtypLabe() {
			return ctypLabe;
		}
		public void setCtypLabe(String ctypLabe) {
			this.ctypLabe = ctypLabe;
		}
		public List<CARD> getCards() {
			return cards;
		}
		public void setCards(List<CARD> cards) {
			this.cards = cards;
		}
		public void setCtypIden(Integer ctypIden) {
			this.ctypIden = ctypIden;
		}
		
		
		public Integer getCtypCode() {
			return ctypCode;
		}
		public void setCtypCode(Integer ctypCode) {
			this.ctypCode = ctypCode;
		}
		
		
		public CARD_TYPE(Integer ctypCode, Integer ctypIden, String ctypLabe, List<CARD> cards) {
			super();
			this.ctypCode = ctypCode;
			this.ctypIden = ctypIden;
			this.ctypLabe = ctypLabe;
			this.cards = cards;
		}
		public CARD_TYPE() {}


		
}
