package com.service;

import java.util.List;
import java.util.Optional;

import com.model.VatRate;

public interface VatRateService {
	List<VatRate> findAll();
    VatRate findByVatCodeAndVatActive(Integer vatCode, Integer vatActive);
    VatRate save(VatRate vatRate);
    void deleteByVatCode(Integer vatCode);
    Optional<VatRate> findById(Integer id);

}
