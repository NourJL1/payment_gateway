package com.service;

import java.util.List;
import com.model.WALLET_OPERATION_TYPE_MAP;


public interface WalletOperationTypeMapService {
	WALLET_OPERATION_TYPE_MAP create(WALLET_OPERATION_TYPE_MAP walletOperationTypeMap);
    WALLET_OPERATION_TYPE_MAP update(Integer id, WALLET_OPERATION_TYPE_MAP walletOperationTypeMap);
    void delete(Integer id);
    WALLET_OPERATION_TYPE_MAP getById(Integer id);
    List<WALLET_OPERATION_TYPE_MAP> getAll();
      List<WALLET_OPERATION_TYPE_MAP> searchWalletOperationTypeMaps(String searchWord);

}
