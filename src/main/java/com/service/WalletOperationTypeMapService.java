package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.model.WALLET_OPERATION_TYPE_MAP;
import com.repository.WalletOperationTypeMapRepository;

import com.model.WALLET_OPERATION_TYPE_MAP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import com.model.*;
import com.repository.*;


public interface WalletOperationTypeMapService {
	WALLET_OPERATION_TYPE_MAP create(WALLET_OPERATION_TYPE_MAP walletOperationTypeMap);
    WALLET_OPERATION_TYPE_MAP update(Integer id, WALLET_OPERATION_TYPE_MAP walletOperationTypeMap);
    void delete(Integer id);
    WALLET_OPERATION_TYPE_MAP getById(Integer id);
    List<WALLET_OPERATION_TYPE_MAP> getAll();
      List<WALLET_OPERATION_TYPE_MAP> searchWalletOperationTypeMaps(String searchWord);

}
