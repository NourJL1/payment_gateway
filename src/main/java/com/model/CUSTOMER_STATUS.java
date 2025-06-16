package com.model;
import jakarta.persistence.*;

import lombok.Data;
@Entity
@Table(name = "customer_status") // Optionnel, mais permet de préciser le nom de la table en base de données
public class CUSTOMER_STATUS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CTS_CODE", nullable = false, unique = true)
    private Integer ctsCode;

    @Column(name = "CTS_IDEN", nullable = false)
    private String ctsIden;

    @Column(name = "CTS_LABE", nullable = false)
    private String ctsLabe;
    
    

	public Integer getCtsCode() {
		return ctsCode;
	}

	public void setCtsCode(Integer ctsCode) {
		this.ctsCode = ctsCode;
	}

	public String getCtsIden() {
		return ctsIden;
	}

	public void setCtsIden(String ctsIden) {
		this.ctsIden = ctsIden;
	}

	public String getCtsLabe() {
		return ctsLabe;
	}

	public void setCtsLabe(String ctsLabe) {
		this.ctsLabe = ctsLabe;
	}

	
	public CUSTOMER_STATUS(Integer ctsCode, String ctsIden, String ctsLabe) {
		super();
		this.ctsCode = ctsCode;
		this.ctsIden = ctsIden;
		this.ctsLabe = ctsLabe;
	}

	public CUSTOMER_STATUS() {}
    
    

}
