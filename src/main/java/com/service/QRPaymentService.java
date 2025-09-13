package com.service;

import dto.QRPaymentRequest;
import dto.QRPaymentResponse;

public interface QRPaymentService {

   byte[] generateQr(QRPaymentRequest request) throws Exception;

    QRPaymentResponse payFromWallet(String senderWalletIden, QRPaymentRequest request) throws Exception;
}
