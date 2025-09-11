package com.servicesImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import com.model.OPERATION_DETAILS;
import com.model.WALLET_OPERATIONS;
import com.service.OperationDetailsService;
import com.repository.OperationsDetailsRepository;
import com.repository.WalletOperationsRepository;

@Service
public class OperationsDetailsServiceImp implements OperationDetailsService {

    @Autowired
    private OperationsDetailsRepository operationDetailsRepository;

    @Autowired
    private WalletOperationsRepository walletOperationsRepository;

    @Override
    public List<OPERATION_DETAILS> findAll() {
        return operationDetailsRepository.findAll();
    }

    @Override
    public Optional<OPERATION_DETAILS> findById(Integer id) {
        return operationDetailsRepository.findById(id);
    }

    @Override
    public OPERATION_DETAILS save(OPERATION_DETAILS operationDetails) {
        WALLET_OPERATIONS walletOp = operationDetails.getWalletOperation();

        if (walletOp == null || walletOp.getWopCode() == null) {
            throw new IllegalArgumentException("walletOperation est requis et doit avoir un ID valide");
        }

        WALLET_OPERATIONS persistedWalletOp = walletOperationsRepository.findById(walletOp.getWopCode())
                .orElseThrow(() -> new IllegalArgumentException(
                        "walletOperation avec ID " + walletOp.getWopCode() + " n'existe pas"));

        operationDetails.setWalletOperation(persistedWalletOp);

        return operationDetailsRepository.save(operationDetails);
    }

    @Override
    public void deleteById(Integer id) {
        operationDetailsRepository.deleteById(id);
    }

    @Override
    public List<OPERATION_DETAILS> searchOperationDetails(String searchWord) {
        if (searchWord == null || searchWord.trim().isEmpty()) {
            return operationDetailsRepository.findAll();
        }
        return operationDetailsRepository.searchOperationDetails(searchWord);
    }

    @Override
    public List<OPERATION_DETAILS> findRecentTransactionsByCustomerAndWallet(Integer cusCode, String walIden, Integer hours) {
        System.out.println("Searching transactions for customer code: " + cusCode + ", wallet identifier: " + walIden + ", hours: " + hours);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, -hours);
        Date cutoffDate = calendar.getTime();

        List<OPERATION_DETAILS> transactions =
                operationDetailsRepository.findByCustomerCodeAndWalletIdenAndDateAfter(cusCode, walIden, cutoffDate);
        System.out.println("Found " + transactions.size() + " recent transactions for customer " + cusCode + " with wallet " + walIden);

        return transactions;
    }

    @Override
    public List<OPERATION_DETAILS> findByWalletIden(String walIden) {
        return operationDetailsRepository.findByWalletIden(walIden);
    }

    
}
