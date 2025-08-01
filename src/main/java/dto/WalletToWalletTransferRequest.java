package dto;

import java.math.BigDecimal;
import lombok.Data;

@Data

public class WalletToWalletTransferRequest {
    private String senderWalletIden;
    private String receiverWalletIden;
    private BigDecimal amount;
    private String operationTypeIden;
	public String getSenderWalletIden() {
		return senderWalletIden;
	}
	public void setSenderWalletIden(String senderWalletIden) {
		this.senderWalletIden = senderWalletIden;
	}
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
	public String getOperationTypeIden() {
		return operationTypeIden;
	}
	public void setOperationTypeIden(String operationTypeIden) {
		this.operationTypeIden = operationTypeIden;
	}
	public WalletToWalletTransferRequest(String senderWalletIden, String receiverWalletIden, BigDecimal amount,
			String operationTypeIden) {
		super();
		this.senderWalletIden = senderWalletIden;
		this.receiverWalletIden = receiverWalletIden;
		this.amount = amount;
		this.operationTypeIden = operationTypeIden;
	}

    

}
