package com.service;
import com.model.CITY;
import java.util.List;
import java.util.Optional;
public interface CityService {
	 List<CITY> findAll();
	    Optional<CITY> findById(Integer id);
	    CITY save(CITY city);
	    void deleteById(Integer id);
      List<CITY> searchCities(String searchWord);

}
