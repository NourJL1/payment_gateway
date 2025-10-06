package com.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity
@Table(name = "PRODUCT")
public class PRODUCT {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRO_CODE", unique = true, nullable = false)
    private Integer proCode;

    @Column(name = "PRO_IDEN", nullable = false, unique = true)
    private String proIden;

    @Column(name = "PRO_NAME", nullable = false, length = 255)
    private String proName;

    @Column(name = "PRO_DESCRIPTION", length = 1000)
    private String proDescription;

    @Column(name = "PRO_PRICE", nullable = false)
    private BigDecimal proPrice;

    @Column(name = "PRO_STOCK_QUANTITY")
    private Integer proStockQuantity;

    @Column(name = "PRO_IS_ACTIVE")
    private Boolean proIsActive = true;

    @Column(name = "CREATED_AT", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "PRO_WAL_CODE", referencedColumnName = "WAL_CODE")
    private WALLET wallet;

    // Single PrePersist method
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

        if (this.proIden == null || this.proIden.isEmpty()) {
            this.proIden = "PRO-" +
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmm")) +
                    "-" + UUID.randomUUID().toString().substring(0, 4).toUpperCase();
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // Constructors
    public PRODUCT() {}

    public PRODUCT(Integer proCode, String proIden, String proName, String proDescription,
                   BigDecimal proPrice, Integer proStockQuantity, Boolean proIsActive,
                   LocalDateTime createdAt, LocalDateTime updatedAt, WALLET wallet) {
        this.proCode = proCode;
        this.proIden = proIden;
        this.proName = proName;
        this.proDescription = proDescription;
        this.proPrice = proPrice;
        this.proStockQuantity = proStockQuantity;
        this.proIsActive = proIsActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.wallet = wallet;
    }

    // Getters and Setters
    public Integer getProCode() { return proCode; }
    public void setProCode(Integer proCode) { this.proCode = proCode; }

    public String getProIden() { return proIden; }
    public void setProIden(String proIden) { this.proIden = proIden; }

    public String getProName() { return proName; }
    public void setProName(String proName) { this.proName = proName; }

    public String getProDescription() { return proDescription; }
    public void setProDescription(String proDescription) { this.proDescription = proDescription; }

    public BigDecimal getProPrice() { return proPrice; }
    public void setProPrice(BigDecimal proPrice) { this.proPrice = proPrice; }

    public Integer getProStockQuantity() { return proStockQuantity; }
    public void setProStockQuantity(Integer proStockQuantity) { this.proStockQuantity = proStockQuantity; }

    public Boolean getProIsActive() { return proIsActive; }
    public void setProIsActive(Boolean proIsActive) { this.proIsActive = proIsActive; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public WALLET getWallet() { return wallet; }
    public void setWallet(WALLET wallet) { this.wallet = wallet; }
}
