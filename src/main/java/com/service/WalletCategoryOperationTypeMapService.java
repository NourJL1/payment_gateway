package com.service;
import com.model.WALLET_CATEGORY_OPERATION_TYPE_MAP;
import java.util.List;
import java.util.Optional;
import com.model.*;

public interface WalletCategoryOperationTypeMapService {
	List<WALLET_CATEGORY_OPERATION_TYPE_MAP> getAll();
	WALLET_CATEGORY_OPERATION_TYPE_MAP getById(Integer id);
	WALLET_CATEGORY_OPERATION_TYPE_MAP create(WALLET_CATEGORY_OPERATION_TYPE_MAP mapping);
	WALLET_CATEGORY_OPERATION_TYPE_MAP update(Integer id, WALLET_CATEGORY_OPERATION_TYPE_MAP mapping);
    void delete(Integer id);
      List<WALLET_CATEGORY_OPERATION_TYPE_MAP> searchWalletCategoryOperationTypeMaps(String searchWord);
    }
