package com.model;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.model.IdentificationType;

import com.model.Role;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "customers", uniqueConstraints = {@UniqueConstraint(columnNames = "CUS_CODE"), @UniqueConstraint(columnNames = "username")})
@Data // Utilisation de Lombok pour générer les getters et setters

public class CUSTOMER {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUS_CODE")
    private Integer cusCode;

    private String cusFirstName;
    private String cusMidName;
    private String cusLastName;
    private String username;
    private String cusMailAddress;
    @JsonProperty("Password")
    private String cusMotDePasse;
    private String cusPhoneNbr;
	private String cusAddress;
    private String cusIden ;
    private Integer cusFinId;
    
    
    // Relation 1..0-* avec CUSTOMER_CONTACTS
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<CUSTOMER_CONTACTS> contacts;

    // Relation *..1 avec CUSTOMER_STATUS
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CUS_CTS_CODE",nullable = false, referencedColumnName = "CTS_CODE")
    private CUSTOMER_STATUS status;

    // Relation *..1 avec CUSTOMER_IDENTITY
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CUS_CID_CODE",nullable = false,referencedColumnName = "CID_CODE")
    private CUSTOMER_IDENTITY identity;
    
    /* @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>(); */
	@ManyToOne
	private Role role;

	// Relation *..1 avec CITY
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "CUS_CTY_CODE", referencedColumnName = "CTY_CODE")
    private CITY city;
    
    

    // Relation *..1 avec COUNTRY (corrigée)
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "CUS_CTR_CODE", referencedColumnName = "CTR_CODE")
    private COUNTRY country;
    
    @Enumerated(EnumType.STRING)
    private IdentificationType identificationType;

    

	public IdentificationType getIdentificationType() {
		return identificationType;
	}

	public void setIdentificationType(IdentificationType identificationType) {
		this.identificationType = identificationType;
	}

	

	



	// Relation 1..* avec WALLET
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude

    private List<WALLET> wallets;
    

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WALLET_OPERATIONS> walletOperations;
    
    

	public List<WALLET_OPERATIONS> getWalletOperations() {
		return walletOperations;
	}

	public void setWalletOperations(List<WALLET_OPERATIONS> walletOperations) {
		this.walletOperations = walletOperations;
	}

	public String getCusFirstName() {
		return cusFirstName;
	}

	public void setCusFirstName(String cusFirstName) {
		this.cusFirstName = cusFirstName;
	}

	public String getCusMidName() {
		return cusMidName;
	}

	public void setCusMidName(String cusMidName) {
		this.cusMidName = cusMidName;
	}

	public String getCusLastName() {
		return cusLastName;
	}

	public void setCusLastName(String cusLastName) {
		this.cusLastName = cusLastName;
	}

	public String getCusMailAddress() {
		return cusMailAddress;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setCusMailAddress(String cusMailAddress) {
		this.cusMailAddress = cusMailAddress;
	}

	public String getCusMotDePasse() {
		return cusMotDePasse;
	}

	public void setCusMotDePasse(String cusMotDePasse) {
		this.cusMotDePasse = cusMotDePasse;
	}

	public String getCusPhoneNbr() {
		return cusPhoneNbr;
	}

	public void setCusPhoneNbr(String cusPhoneNbr) {
		this.cusPhoneNbr = cusPhoneNbr;
	}

	public String getCusAddress() {
		return cusAddress;
	}

	public void setCusAddress(String cusAddress) {
		this.cusAddress = cusAddress;
	}

	public String getCusIden() {
		return cusIden;
	}

	public void setCusIden(String cusIden) {
		this.cusIden = cusIden;
	}

	public Integer getCusFinId() {
		return cusFinId;
	}

	public void setCusFinId(Integer cusFinId) {
		this.cusFinId = cusFinId;
	}

	public List<CUSTOMER_CONTACTS> getContacts() {
		return contacts;
	}

	public void setContacts(List<CUSTOMER_CONTACTS> contacts) {
		this.contacts = contacts;
	}

	public CUSTOMER_STATUS getStatus() {
		return status;
	}

	public void setStatus(CUSTOMER_STATUS status) {
		this.status = status;
	}

	public CUSTOMER_IDENTITY getIdentity() {
		return identity;
	}

	public void setIdentity(CUSTOMER_IDENTITY identity) {
		this.identity = identity;
	}

	

	public List<WALLET> getWallets() {
		return wallets;
	}

	public void setWallets(List<WALLET> wallets) {
		this.wallets = wallets;
	}

	
	public Integer getCusCode() {
		return cusCode;
	}

	public void setCusCode(Integer cusCode) {
		this.cusCode = cusCode;
	}

	
	
	
	public CUSTOMER(Integer cusCode, String cusFirstName, String cusMidName, String cusLastName, String cusMailAddress,
			String cusMotDePasse, String cusPhoneNbr, String cusAddress, String cusIden, Integer cusFinId,
			List<CUSTOMER_CONTACTS> contacts, CUSTOMER_STATUS status, CUSTOMER_IDENTITY identity, CITY city,
			COUNTRY country, List<WALLET> wallets, List<WALLET_OPERATIONS> walletOperations) {
		super();
		this.cusCode = cusCode;
		this.cusFirstName = cusFirstName;
		this.cusMidName = cusMidName;
		this.cusLastName = cusLastName;
		this.cusMailAddress = cusMailAddress;
		this.cusMotDePasse = cusMotDePasse;
		this.cusPhoneNbr = cusPhoneNbr;
		this.cusAddress = cusAddress;
		this.cusIden = cusIden;
		this.cusFinId = cusFinId;
		this.contacts = contacts;
		this.status = status;
		this.identity = identity;
		this.city = city;
		this.country = country;
		this.wallets = wallets;
		this.walletOperations = walletOperations;
	}

	public CUSTOMER() {}

	



	public CITY getCity() {
		return city;
	}

	public void setCity(CITY city) {
		this.city = city;
	}

	public COUNTRY getCountry() {
		return country;
	}

	public void setCountry(COUNTRY country) {
		this.country = country;
	}

	public Role getRole() {
		return this.role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	@JsonProperty("fullName")
	public String getFullName() {
	    StringBuilder fullName = new StringBuilder();

	    if (cusFirstName != null) fullName.append(cusFirstName).append(" ");
	    if (cusMidName != null) fullName.append(cusMidName).append(" ");
	    if (cusLastName != null) fullName.append(cusLastName);

	    return fullName.toString().trim();
	}
	
	 

  public String getName() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getName'");
  }

}
	
	
    
    
    
    

