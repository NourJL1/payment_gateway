package com.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "CARD", uniqueConstraints = {
    @UniqueConstraint(columnNames = "CAR_NUMB")
})
@Data

public class CARD {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CAR_CODE", nullable = false, unique = true)
	private Integer carCode;

	@Column(name = "CAR_NUMB", nullable = false)
	private String carNumb;

	@Column(name = "CAR_IDEN", nullable = false)
	private String carIden;

	@Column(name = "CAR_EXPIRY_DATE", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date carExpiryDate;

	@Column(name = "CAR_EMV_DATA")
	private String carEmvData;

	@Column(name = "CAR_LABE", nullable = false)
	private String carLabe;

  // 🔹 Nouveau champ : Montant de la carte
    @Column(name = "CAR_AMOUNT")
    private Float carAmount;

    // 🔹 Nouveau champ : Plafond
    @Column(name = "CAR_PLAFOND")
    private Float carPlafond;

    // 🔹 Nouveau champ : Périodicité du plafond (DAY, WEEK, MONTH)
    @Column(name = "CAR_PLAFOND_PERIOD", length = 20)
    private String carPlafondPeriod;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "CAR_CTYP_CODE", nullable = false)
	@JsonIgnoreProperties("cards") // ← empêche le renvoi de la liste des cartes depuis CARD_TYPE

	private CARD_TYPE cardType;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "CAR_CLI_CODE", nullable = false)

	@JsonIgnoreProperties("cards") // ← empêche le renvoi de la liste des cartes depuis CARD_TYPE

	private CARD_LIST cardList;


  public Float getCarAmount() {
        return carAmount;
    }

    public void setCarAmount(Float carAmount) {
        this.carAmount = carAmount;
    }

    public Float getCarPlafond() {
        return carPlafond;
    }

    public void setCarPlafond(Float carPlafond) {
        this.carPlafond = carPlafond;
    }

    public String getCarPlafondPeriod() {
        return carPlafondPeriod;
    }

    public void setCarPlafondPeriod(String carPlafondPeriod) {
        this.carPlafondPeriod = carPlafondPeriod;
    }

	public Integer getCarCode() {
		return carCode;
	}

	public void setCarCode(Integer carCode) {
		this.carCode = carCode;
	}

	public String getCarIden() {
		return carIden;
	}

	public void setCarIden(String carIden) {
		this.carIden = carIden;
	}

	@PrePersist
	public void setCarIden() {
		this.carIden = "CAR-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmm")) + "-"
				+ UUID.randomUUID().toString().substring(0, 4).toUpperCase();
	}

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

	public CARD(Integer carCode, String carNumb, String carIden, Date carExpiryDate, String carEmvData,
			String carLabe, CARD_TYPE cardType, CARD_LIST cardList, Float carAmount, Float carPlafond, String carPlafondPeriod) {
		super();
		this.carCode = carCode;
		this.carNumb = carNumb;
		this.carIden = carIden;
		this.carExpiryDate = carExpiryDate;
		this.carEmvData = carEmvData;
		this.carLabe = carLabe;
		this.cardType = cardType;
		this.cardList = cardList;
    this.carAmount = carAmount;
    this.carPlafond = carPlafond;
    this.carPlafondPeriod = carPlafondPeriod;
	}

	public CARD() {
	}

}
