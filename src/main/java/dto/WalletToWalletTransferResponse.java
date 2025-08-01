package dto;

import java.math.BigDecimal;

import lombok.Data;

@Data

public class WalletToWalletTransferResponse {
     private String transactionId;
    private BigDecimal totalDebited;
    private String message;
    public WalletToWalletTransferResponse(String transactionId, BigDecimal totalDebited, String message) {
        this.transactionId = transactionId;
        this.totalDebited = totalDebited;
        this.message = message;
    }
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public BigDecimal getTotalDebited() {
		return totalDebited;
	}
	public void setTotalDebited(BigDecimal totalDebited) {
		this.totalDebited = totalDebited;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

    

}
