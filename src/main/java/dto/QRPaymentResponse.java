package dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor

public class QRPaymentResponse {

    private String transactionId;
    private String status;
    private String message;

}
