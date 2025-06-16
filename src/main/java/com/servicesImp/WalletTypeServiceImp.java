package com.servicesImp;
import com.model.WALLET_TYPE;
import com.repository.WalletTypeRepository;
import com.service.WalletTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class WalletTypeServiceImp implements WalletTypeService {

	@Autowired
    private WalletTypeRepository walletTypeRepository;

    @Override
    public WALLET_TYPE createWalletType(WALLET_TYPE walletType) {
        return walletTypeRepository.save(walletType);
    }

    @Override
    public List<WALLET_TYPE> getAllWalletTypes() {
        return walletTypeRepository.findAll();
    }

    @Override
    public Optional<WALLET_TYPE> getWalletTypeById(Integer wtyCode) {
        return walletTypeRepository.findById(wtyCode);
    }

    @Override
    public WALLET_TYPE updateWalletType(Integer wtyCode, WALLET_TYPE walletType) {
        return walletTypeRepository.findById(wtyCode)
                .map(existingWalletType -> {
                    existingWalletType.setWtyIden(walletType.getWtyIden());
                    existingWalletType.setWtyLabe(walletType.getWtyLabe());
                    return walletTypeRepository.save(existingWalletType);
                })
                .orElseThrow(() -> new RuntimeException("WalletType not found"));
    }

    @Override
    public void deleteWalletType(Integer wtyCode) {
        walletTypeRepository.deleteById(wtyCode);
    }

    @Override
    public List<WALLET_TYPE> searchWalletTypes(String searchWord) {
        if (searchWord == null || searchWord.trim().isEmpty()) {
            return walletTypeRepository.findAll();
        }
        return walletTypeRepository.searchWalletTypes(searchWord);
    }

}
