package com.servicesImp;
import com.model.*;

import com.repository.FeesRepository;
import com.service.FeesService;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service

public class FeesServiceImp implements FeesService {

	@Autowired
    private FeesRepository repository;

    @Override
    public List<FEES> getAll() {
        return repository.findAll();
    }

    @Override
    public FEES getById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fees not found with id: " + id));
    }

    @Override
    public FEES create(FEES fees) {
        return repository.save(fees);
    }

    @Override
    public FEES update(Integer id, FEES fees) {
        FEES existingFees = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fees not found with id: " + id));

        existingFees.setFeeLabel(fees.getFeeLabel());
        existingFees.setFeeAmount(fees.getFeeAmount());
        existingFees.setFeePercentage(fees.getFeePercentage());
        existingFees.setFeeMinLimit(fees.getFeeMinLimit());
        existingFees.setFeeMaxLimit(fees.getFeeMaxLimit());
        existingFees.setFeeMaxAmount(fees.getFeeMaxAmount());
        existingFees.setFinancialInstitutionId(fees.getFinancialInstitutionId());

        // Mise à jour de la relation avec WALLET_OPERATION_TYPE_MAP si nécessaire
        if (fees.getWalletCategoryOperationTypeMaps() != null) {
            existingFees.getWalletCategoryOperationTypeMaps().clear();
            existingFees.getWalletCategoryOperationTypeMaps().addAll(fees.getWalletCategoryOperationTypeMaps());
        }

        return repository.save(existingFees);
    }

    @Override
    public void delete(Integer id) {
        FEES fees = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fees not found with id: " + id));

        repository.delete(fees);
    }

}