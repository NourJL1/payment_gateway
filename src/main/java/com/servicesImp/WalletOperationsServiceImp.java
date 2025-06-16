package com.servicesImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.WALLET_OPERATIONS;
import com.service.WalletOperationsService;
import com.repository.WalletOperationsRepository;

import java.util.List;
import java.util.Optional;
@Service
public class WalletOperationsServiceImp implements WalletOperationsService{
	@Autowired
    private WalletOperationsRepository repository;

	@Override
	public List<WALLET_OPERATIONS> getAllOperations() {
        return repository.findAll();

	}

	@Override
	public Optional<WALLET_OPERATIONS> getOperationById(Integer WOP_CODE) {
        return repository.findById(WOP_CODE);

	}

	@Override
	public WALLET_OPERATIONS createOperation(WALLET_OPERATIONS operation) {
	    if (operation.getWopAmount() == null) {
	        throw new IllegalArgumentException("Amount cannot be null");
	    }
	    return repository.save(operation);
	}

	@Override
	public WALLET_OPERATIONS updateOperation(Integer WOP_CODE, WALLET_OPERATIONS operationDetails) {
		 return repository.findById(WOP_CODE).map(operation -> {
	            operation.setWopIden(operationDetails.getWopIden());
	            operation.setWopOtyCode(operationDetails.getWopOtyCode());
	            operation.setWopAmount(operationDetails.getWopAmount());
	            operation.setWopCurrency(operationDetails.getWopCurrency());
	            operation.setWopStatus(operationDetails.getWopStatus());
	            operation.setWopLabel(operationDetails.getWopLabel());
	            operation.setWopTimestamps(operationDetails.getWopTimestamps());
	            return repository.save(operation);
	        }).orElseThrow(() -> new RuntimeException("Operation not found with WOP_CODE: " + WOP_CODE));
	}

	@Override
	public void deleteOperation(Integer WOP_CODE) {
        repository.deleteById(WOP_CODE);
		
	}

	/*@Override
	public List<WALLET_OPERATIONS> getOperationsByWalletCode(Integer walletCode) {
        return repository.findByWOP_WAL_CODE(walletCode);

	}

	@Override
	public List<WALLET_OPERATIONS> getOperationsByCustomerCode(Integer customerCode) {
        return repository.findByWOP_CUS_CODE(customerCode);

	}

	@Override
	public List<WALLET_OPERATIONS> getOperationsByStatus(String status) {
        return repository.findByWOP_STATUS(status);

	}

	@Override
	public List<WALLET_OPERATIONS> getOperationsByWalletSortedByTimestamp(Integer walletCode) {
        return repository.findByWOP_WAL_CODEOrderByWOP_TIMESTAMPDesc(walletCode);

	}*/
  @Override
    public List<WALLET_OPERATIONS> searchWalletOperations(String searchWord) {
        if (searchWord == null || searchWord.trim().isEmpty()) {
            return repository.findAll();
        }
        return repository.searchWalletOperations(searchWord);
    }

	
	
}
