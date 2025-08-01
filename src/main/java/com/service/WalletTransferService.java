package com.service;

import java.math.BigDecimal;

import dto.WalletToWalletTransferRequest;
import dto.WalletToWalletTransferResponse;

public interface WalletTransferService {

    WalletToWalletTransferResponse transfer(WalletToWalletTransferRequest request);

}
