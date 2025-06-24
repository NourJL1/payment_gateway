package com.servicesImp;
import com.model.CITY;
import com.model.COUNTRY;
import com.repository.CityRepository;
import com.repository.CountryRepository;
import com.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class CityServiceImp implements CityService {

    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private CountryRepository countryRepository; // Injection du repository pour récupérer le pays


	@Override
	public List<CITY> findAll() {
		return cityRepository.findAll();
	}

	@Override
	public Optional<CITY> findById(Integer id) {
		return cityRepository.findById(id);
	}
    

	@Override
    public CITY save(CITY city) {
        if (city.getCountry() == null || city.getCountry().getCtrCode() == null) {
            throw new RuntimeException("Country must be specified before saving the city");
        }

        // Vérifier si le pays existe en base
        countryRepository.findById(city.getCountry().getCtrCode()).ifPresentOrElse(
            city::setCountry,
            () -> { throw new RuntimeException("Country with ID " + city.getCountry().getCtrCode() + " not found"); }
        );

        return cityRepository.save(city);
    }

	@Override
	public void deleteById(Integer id) {
		cityRepository.deleteById(id);
		
	}
  

    @Override
    public List<CITY> searchCities(String searchWord) {
        if (searchWord == null || searchWord.trim().isEmpty()) {
            return cityRepository.findAll();
        }
        return cityRepository.searchCities(searchWord);

}

    @Override
    public List<CITY> findByCountry(COUNTRY country) {
        return cityRepository.findByCountry(country);
    }}
