package com.servicesImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.QRPaymentService;
import com.service.WalletTransferService;
import com.util.QrCodeUtil;

import dto.QRPaymentRequest;
import dto.WalletToWalletTransferRequest;
import dto.QRPaymentResponse;

@Service
public class QrPaymentServiceImpl implements QRPaymentService {

    @Autowired
    private WalletTransferService walletTransferService;

    @Override
    public byte[] generateQr(QRPaymentRequest request) throws Exception {
        // Transformer le payload en JSON
        String payload = "{"
                + "\"receiverWalletIden\":\"" + request.getReceiverWalletIden() + "\","
                + "\"amount\":\"" + request.getAmount() + "\","
                + "\"currency\":\"" + request.getCurrency() + "\","
                + "\"label\":\"" + request.getLabel() + "\","
                + "\"expiresAt\":\"" + request.getExpiresAt() + "\""
                + "}";
        return QrCodeUtil.generateQrCode(payload, 250, 250);
    }

    @Override
    public QRPaymentResponse payFromWallet(String senderWalletIden, QRPaymentRequest request) throws Exception {
        if (request.getExpiresAt() != null && System.currentTimeMillis() > request.getExpiresAt()) {
            return new QRPaymentResponse(null, "FAILED", "QR code expiré");
        }

        // Transfert via WalletTransferService
        walletTransferService.transfer(
    new WalletToWalletTransferRequest(
        "OPT-003",                  // operationTypeIden
        senderWalletIden,                  // senderWalletIden
        request.getReceiverWalletIden(),   // receiverWalletIden
        request.getAmount()                // amount
    )
);


        return new QRPaymentResponse("TXN-" + System.currentTimeMillis(), "SUCCESS", "Paiement effectué");
    }

}
