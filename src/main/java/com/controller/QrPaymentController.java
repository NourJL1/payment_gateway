package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.*;

import dto.QRPaymentRequest;
import dto.QRPaymentResponse;

@RestController
@RequestMapping("/api/qr")

public class QrPaymentController {
    @Autowired
    private QRPaymentService qrPaymentService;

    @PostMapping(value = "/generate", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> generateQr(@RequestBody QRPaymentRequest request) throws Exception {
        byte[] qr = qrPaymentService.generateQr(request);
        return ResponseEntity.ok().body(qr);
    }

    @PostMapping("/pay/{senderWalletIden}")
    public ResponseEntity<QRPaymentResponse> payFromWallet(
            @PathVariable String senderWalletIden,
            @RequestBody QRPaymentRequest request) throws Exception {
        QRPaymentResponse response = qrPaymentService.payFromWallet(senderWalletIden, request);
        return ResponseEntity.ok(response);
    }

}
