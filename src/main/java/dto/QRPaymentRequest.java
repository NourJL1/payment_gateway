package dto;

import java.math.BigDecimal;

public class QRPaymentRequest {

    private String receiverWalletIden;
    private BigDecimal amount;
    private String currency;
    private String label; // optionnel
    private Long expiresAt; // timestamp en millis

    // getters / setters
    public String getReceiverWalletIden() {
        return receiverWalletIden;
    }

    public void setReceiverWalletIden(String receiverWalletIden) {
        this.receiverWalletIden = receiverWalletIden;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Long expiresAt) {
        this.expiresAt = expiresAt;
    }

}
