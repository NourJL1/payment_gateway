package com.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

@Entity
@Table(name = "CUSTOMER_CONTACTS")
@Data

public class CUSTOMER_CONTACTS {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CCO_CODE", nullable = false, unique = true)
    private Integer ccoCode;

    @Column(name = "CCO_IDEN", nullable = false)
    private Integer ccoIden;

    @Column(name = "CCO_CONTACT_NAME", nullable = false)
    private String ccoContactName;

    @Column(name = "CCO_CONTACT_MAIL", nullable = false, unique = true)
    private String ccoContactMail;

    @Column(name = "CCO_CONTACT_PHONE", nullable = false)
    private String ccoContactPhone;

    //@Column(name = "CCO_CONTACT_WALLET_ID")
    //private Long ccoContactWalletId;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CCO_ADDED_AT", nullable = false, updatable = false)
    private Date ccoAddedAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CCO_LAST_INTERACTION")
    private Date ccoLastInteraction;

    @ManyToOne
    @JoinColumn(name = "CCO_CUS_CODE", referencedColumnName = "CUS_CODE", nullable = false)
    @JsonIgnore

    private CUSTOMER customer;
    
    

	public Integer getCcoIden() {
		return ccoIden;
	}

	public void setCcoIden(Integer ccoIden) {
		this.ccoIden = ccoIden;
	}

	public String getCcoContactName() {
		return ccoContactName;
	}

	public void setCcoContactName(String ccoContactName) {
		this.ccoContactName = ccoContactName;
	}

	public String getCcoContactMail() {
		return ccoContactMail;
	}

	public void setCcoContactMail(String ccoContactMail) {
		this.ccoContactMail = ccoContactMail;
	}

	public String getCcoContactPhone() {
		return ccoContactPhone;
	}

	public void setCcoContactPhone(String ccoContactPhone) {
		this.ccoContactPhone = ccoContactPhone;
	}

	public Date getCcoAddedAt() {
		return ccoAddedAt;
	}

	public void setCcoAddedAt(Date ccoAddedAt) {
		this.ccoAddedAt = ccoAddedAt;
	}

	public Date getCcoLastInteraction() {
		return ccoLastInteraction;
	}

	public void setCcoLastInteraction(Date ccoLastInteraction) {
		this.ccoLastInteraction = ccoLastInteraction;
	}

	public CUSTOMER getCustomer() {
		return customer;
	}

	public void setCustomer(CUSTOMER customer) {
		this.customer = customer;
	}

	public CUSTOMER_CONTACTS(Integer ccoIden, String ccoContactName, String ccoContactMail, String ccoContactPhone,
			Date ccoAddedAt, Date ccoLastInteraction, CUSTOMER customer) {
		super();
		this.ccoIden = ccoIden;
		this.ccoContactName = ccoContactName;
		this.ccoContactMail = ccoContactMail;
		this.ccoContactPhone = ccoContactPhone;
		this.ccoAddedAt = ccoAddedAt;
		this.ccoLastInteraction = ccoLastInteraction;
		this.customer = customer;
	}
	
	public CUSTOMER_CONTACTS() {}
    
    
    
   


		
	}
    
    
    

