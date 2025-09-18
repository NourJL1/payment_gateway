package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.service.QRPaymentService;
import com.util.QrCodeUtil;
import com.service.WalletTransferService;
import dto.QRPaymentRequest;
import dto.QRPaymentResponse;
import dto.WalletToWalletTransferRequest;
import dto.WalletToWalletTransferResponse;

import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.io.IOException;

@RestController
@RequestMapping("/api/qr")
public class QrPaymentController {
    @Autowired
    private QRPaymentService qrPaymentService;

    @Autowired
    private WalletTransferService walletTransferService;

    @PostMapping(value = "/generate", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> generateQr(@RequestBody QRPaymentRequest request) throws Exception {
        byte[] qr = qrPaymentService.generateQr(request);
        return ResponseEntity.ok().body(qr);
    }

    @PostMapping(value = "/pay/{senderWalletIden}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QRPaymentResponse> payFromWallet(
            @PathVariable String senderWalletIden,
            @RequestBody QRPaymentRequest request) throws Exception {
        QRPaymentResponse response = qrPaymentService.payFromWallet(senderWalletIden, request);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/decode", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> decodeQr(@RequestParam("file") MultipartFile file) {
        try {
            String decodedText = QrCodeUtil.decodeQrCode(file.getBytes());
            Map<String, String> response = new HashMap<>();
            response.put("data", decodedText);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("error", "Failed to decode QR code: " + e.getMessage()));
        }
    }

    @PostMapping(value = "/transfer/{senderWalletIden}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QRPaymentResponse> qrTransfer(
            @PathVariable String senderWalletIden,
            @RequestBody QRPaymentRequest request) throws Exception {
        // Check if QR code is expired
        if (request.getExpiresAt() != null && System.currentTimeMillis() > request.getExpiresAt()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new QRPaymentResponse(null, "FAILED", "QR code expired"));
        }

        try {
            // Perform the transfer using WalletTransferService
            WalletToWalletTransferResponse transferResponse = walletTransferService.transfer(
                    new WalletToWalletTransferRequest(
                            "OPT-003",
                            senderWalletIden,
                            request.getReceiverWalletIden(),
                            request.getAmount()
                    )
            );

            // Map WalletToWalletTransferResponse to QRPaymentResponse
            QRPaymentResponse response = new QRPaymentResponse(
                    transferResponse.getTransactionId(),
                    "SUCCESS",
                    transferResponse.getMessage()
            );

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new QRPaymentResponse(null, "FAILED", "Transfer failed: " + e.getMessage()));
        }
    }
}