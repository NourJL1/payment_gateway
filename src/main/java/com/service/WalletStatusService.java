package com.service;
import com.model.WALLET_STATUS;
import java.util.List;
import java.util.Optional;

public interface WalletStatusService {
	List<WALLET_STATUS> getAllWalletStatuses();
    Optional<WALLET_STATUS> getWalletStatusById(Integer wstCode);
    WALLET_STATUS createWalletStatus(WALLET_STATUS walletStatus);
    WALLET_STATUS updateWalletStatus(Integer wstCode, WALLET_STATUS walletStatusDetails);
    void deleteWalletStatus(Integer wstCode);
    List<WALLET_STATUS> searchWalletStatuses(String searchWord);
}
