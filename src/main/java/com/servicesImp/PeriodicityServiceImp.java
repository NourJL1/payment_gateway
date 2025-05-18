package com.servicesImp;
import com.model.PERIODICITY;
import com.repository.PeriodicityRepository;
import com.service.PeriodicityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PeriodicityServiceImp implements PeriodicityService {

	@Autowired
    private PeriodicityRepository repository;

    @Override
    public PERIODICITY createPeriodicity(PERIODICITY periodicity) {
        return repository.save(periodicity);
    }

    @Override
    public List<PERIODICITY> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<PERIODICITY> getById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public PERIODICITY update(Integer id, PERIODICITY periodicity) {
        return repository.findById(id).map(existingEntity -> {
            existingEntity.setPerIden(periodicity.getPerIden());
            existingEntity.setPerLabe(periodicity.getPerLabe());

            // Mise à jour de la relation avec WALLET_OPERATION_TYPE_MAP
            if (periodicity.getWalletOperationTypeMaps() != null) {
                existingEntity.getWalletOperationTypeMaps().clear();
                existingEntity.getWalletOperationTypeMaps().addAll(periodicity.getWalletOperationTypeMaps());
            }

            return repository.save(existingEntity);
        }).orElseThrow(() -> new RuntimeException("Periodicity not found"));
    }
    @Override
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Periodicity not found");
        }
        repository.deleteById(id);
    }


}
