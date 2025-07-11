package com.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ACCOUNT_TYPE")
@Data
public class ACCOUNT_TYPE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ATY_CODE", nullable = false, unique = true)
    private Integer atyCode;

    @Column(name = "ATY_IDEN", nullable = false)
    private String atyIden;

    @Column(name = "ATY_LABE", nullable = false)
    private String atyLabe;

    @Column(name = "ATY_FIN_ID", nullable = false)
    private Integer atyFinId;

    // Relation avec ACCOUNT (1 → 0..*)

    @OneToMany(mappedBy = "accountType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ACCOUNT> accounts;

    // Constructeur par défaut
    public ACCOUNT_TYPE() {
    }

    public ACCOUNT_TYPE(Integer atyCode, String atyIden, String atyLabe, Integer atyFinId, List<ACCOUNT> accounts) {
        super();
        this.atyCode = atyCode;
        this.atyIden = atyIden;
        this.atyLabe = atyLabe;
        this.atyFinId = atyFinId;
        this.accounts = accounts;
    }

    public Integer getAtyCode() {
        return atyCode;
    }

    public void setAtyCode(Integer atyCode) {
        this.atyCode = atyCode;
    }

    // Getters & Setters
    public String getAtyIden() {
        return atyIden;
    }

    public void setAtyIden(String atyIden) {
        this.atyIden = atyIden;
    }

    public String getAtyLabe() {
        return atyLabe;
    }

    public void setAtyLabe(String atyLabe) {
        this.atyLabe = atyLabe;
    }

    @PrePersist
    public void setAtyIden() {
        atyIden = "ATY" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmm")) + "-"
                + UUID.randomUUID().toString().substring(0, 4).toUpperCase();
    }

    public Integer getAtyFinId() {
        return atyFinId;
    }

    public void setAtyFinId(Integer atyFinId) {
        this.atyFinId = atyFinId;
    }

    public List<ACCOUNT> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<ACCOUNT> accounts) {
        this.accounts = accounts;
    }

}
