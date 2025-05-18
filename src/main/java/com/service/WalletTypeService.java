package com.service;
import java.util.List;
import java.util.Optional;
import com.model.*;


public interface WalletTypeService {
	WALLET_TYPE createWalletType(WALLET_TYPE walletType);
    List<WALLET_TYPE> getAllWalletTypes();
    Optional<WALLET_TYPE> getWalletTypeById(Integer wtyCode);
    WALLET_TYPE updateWalletType(Integer wtyCode, WALLET_TYPE walletType);
    void deleteWalletType(Integer wtyCode);
}
