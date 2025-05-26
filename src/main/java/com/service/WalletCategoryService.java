package com.service;
import com.model.WALLET_CATEGORY;
import java.util.List;
import java.util.Optional;

public interface WalletCategoryService {
	 WALLET_CATEGORY createWalletCategory(WALLET_CATEGORY walletCategory);
	    WALLET_CATEGORY updateWalletCategory(Integer id, WALLET_CATEGORY walletCategory);
	    void deleteWalletCategory(Integer id);
	    WALLET_CATEGORY getWalletCategoryById(Integer id);
	    List<WALLET_CATEGORY> getAllWalletCategories();

}
