package com.servicesImp;

import com.model.COUNTRY;
import com.repository.CountryRepository;
import com.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImp implements CountryService {

    private final CountryRepository countryRepository;

    // Constructor Injection (Recommended)
    public CountryServiceImp(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<COUNTRY> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<COUNTRY> findById(Integer id) {
        return countryRepository.findById(id);
    }

    @Override
    public COUNTRY save(COUNTRY country) {
        return countryRepository.save(country);
    }

    @Override
    public void deleteById(Integer id) {
        countryRepository.deleteById(id);
    }
}
