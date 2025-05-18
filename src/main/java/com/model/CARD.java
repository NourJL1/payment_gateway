package com.model;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "CARD")
@Data

public class CARD {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "CAR_CODE", nullable = false, unique = true)
	    private Integer carCode;

	    @Column(name = "CAR_NUMB", nullable = false)
	    private String carNumb;

	    @Column(name = "CAR_EXPIRY_DATE", nullable = false)
	    @Temporal(TemporalType.DATE)
	    private Date carExpiryDate;

	    @Column(name = "CAR_EMV_DATA")
	    private String carEmvData;

	    @Column(name = "CAR_LABE", nullable = false)
	    private String carLabe;

	    

	    @ManyToOne(cascade = CascadeType.PERSIST)
	    @JoinColumn(name = "CAR_CTYP_CODE", nullable = false)
	    @JsonIgnoreProperties("cards") // ← empêche le renvoi de la liste des cartes depuis CARD_TYPE

	    private CARD_TYPE cardType;

	    
	    @ManyToOne(cascade = CascadeType.PERSIST)
	    @JoinColumn(name = "CAR_CLI_CODE", nullable = false)
	    
	    @JsonIgnoreProperties("cards") // ← empêche le renvoi de la liste des cartes depuis CARD_TYPE

	    private CARD_LIST cardList;


		public String getCarNumb() {
			return carNumb;
		}


		public void setCarNumb(String carNumb) {
			this.carNumb = carNumb;
		}


		public Date getCarExpiryDate() {
			return carExpiryDate;
		}


		public void setCarExpiryDate(Date carExpiryDate) {
			this.carExpiryDate = carExpiryDate;
		}


		public String getCarEmvData() {
			return carEmvData;
		}


		public void setCarEmvData(String carEmvData) {
			this.carEmvData = carEmvData;
		}


		public String getCarLabe() {
			return carLabe;
		}


		public void setCarLabe(String carLabe) {
			this.carLabe = carLabe;
		}


		public CARD_TYPE getCardType() {
			return cardType;
		}


		public void setCardType(CARD_TYPE cardType) {
			this.cardType = cardType;
		}


		public CARD_LIST getCardList() {
			return cardList;
		}


		public void setCardList(CARD_LIST cardList) {
			this.cardList = cardList;
		}


		public CARD(String carNumb, Date carExpiryDate, String carEmvData, String carLabe, CARD_TYPE cardType,
				CARD_LIST cardList) {
			super();
			this.carNumb = carNumb;
			this.carExpiryDate = carExpiryDate;
			this.carEmvData = carEmvData;
			this.carLabe = carLabe;
			this.cardType = cardType;
			this.cardList = cardList;
		}
		public CARD() {}

		


		

}
