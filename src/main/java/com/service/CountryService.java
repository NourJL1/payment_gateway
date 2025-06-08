package com.service;
import com.model.COUNTRY;
import java.util.List;
import java.util.Optional;

public interface CountryService {
	 List<COUNTRY> findAll();
	    Optional<COUNTRY> findById(Integer id);
	    COUNTRY save(COUNTRY country);
	    void deleteById(Integer id);

}
