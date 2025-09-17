package dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor

public class QRPaymentResponse {
    private String transactionId;
    private String status;
    private String message;

    

    public String getTransactionId() {
        return transactionId;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}