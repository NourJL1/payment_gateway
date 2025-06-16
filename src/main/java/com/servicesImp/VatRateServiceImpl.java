package com.servicesImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.VatRate;
import com.repository.VatRateRepository;
import com.service.VatRateService;
@Service
public class VatRateServiceImpl implements VatRateService {
	@Autowired
    private VatRateRepository vatRateRepository;

    @Override
    public List<VatRate> findAll() {
        return vatRateRepository.findAll();
    }

    @Override
    public VatRate findByVatCodeAndVatActive(Integer vatCode, Integer vatActive) {
        return vatRateRepository.findByVatCodeAndVatActive(vatCode, vatActive);
    }

    @Override
    public VatRate save(VatRate vatRate) {
        return vatRateRepository.save(vatRate);
    }

    @Override
    public void deleteByVatCode(Integer vatCode) {
        vatRateRepository.deleteById(vatCode);
    }

	@Override
	public Optional<VatRate> findById(Integer id) {
		return vatRateRepository.findById(id);
	}
   
}
