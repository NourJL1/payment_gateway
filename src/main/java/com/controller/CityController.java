package com.controller;
import com.model.CITY;
import com.model.COUNTRY;
import com.service.CityService;
import com.service.CountryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @Autowired
    private CountryService countryService;

    @GetMapping
    public List<CITY> getAllCities() {
        return cityService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CITY> getCityById(@PathVariable Integer id) {
        Optional<CITY> city = cityService.findById(id);
        return city.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/getByCountry/{id}")
    public ResponseEntity<List<CITY>> getMethodName(@PathVariable Integer id) {
        COUNTRY country = countryService.findById(id).orElseThrow(() -> new RuntimeException("Country not found with id: " + id));
        List<CITY> cities = cityService.findByCountry(country);
        return ResponseEntity.ok().body(cities);
    }
    

    @PostMapping
    public ResponseEntity<CITY> createCity(@RequestBody CITY city) {
        System.out.println(city.toString());
        CITY createdCity = cityService.save(city);
        return ResponseEntity.ok(createdCity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CITY> updateCity(@PathVariable Integer id, @RequestBody CITY city) {
        return cityService.findById(id)
            .map(existingCity -> {
                existingCity.setCtyIden(city.getCtyIden());
                existingCity.setCtyLabe(city.getCtyLabe());
                existingCity.setCountry(city.getCountry());
                CITY updatedCity = cityService.save(existingCity);
                return ResponseEntity.ok(updatedCity);
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable Integer id) {
        if (!cityService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        cityService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<CITY>> searchCities(@RequestParam("word") String searchWord) {
        List<CITY> cities = cityService.searchCities(searchWord);
        return ResponseEntity.ok(cities);
    }
}
